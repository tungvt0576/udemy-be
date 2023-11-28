package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.dto.assignment.AssignmentDTO;
import com.team47.udemybackend.dto.assignment.CreateAssignmentDTO;
import com.team47.udemybackend.dto.assignment.UpdateAssignmentDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.dto.response.DataResponse;
import com.team47.udemybackend.exception.UdemyRuntimeException;
import com.team47.udemybackend.exception.UdemyValidateException;
import com.team47.udemybackend.models.Assignment;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.repository.AssignmentRepository;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.service.AssignmentService;
import com.team47.udemybackend.utils.UtilsFunctions;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public DataListResponse listByFilter(int pageSize, int pageNum, Integer courseID, String type) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());

            Page<Assignment> assignmentPage;
            if (courseID  != null) {
                if (type != null && !type.isEmpty()) {
                    assignmentPage = assignmentRepository.findByTypeAndCourse_Id(type, courseID, pageRequest);
                } else {
                    assignmentPage = assignmentRepository.findByCourse_Id(courseID, pageRequest);
                }
            } else {
                if (type != null && !type.isEmpty()) {
                    assignmentPage = assignmentRepository.findByType(type, pageRequest);
                } else {
                    assignmentPage = assignmentRepository.findAll(pageRequest);
                }
            }
            List<AssignmentDTO> assignmentDTOs = assignmentPage.getContent().stream()
                    .map(this::mapToAssignmentDTO)
                    .collect(Collectors.toList());

            return DataListResponse.builder()
                    .isError(false)
                    .dataList(assignmentDTOs)
                    .total(assignmentPage.getTotalElements())
                    .pageSize(pageSize)
                    .pageNum(pageNum)
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }


    @Override
    public DataListResponse listAll() {
        try {
            List<AssignmentDTO> assignments = new ArrayList<>();
            assignmentRepository.findAll().forEach(assignment -> assignments.add(mapToAssignmentDTO(assignment)));
            return DataListResponse.builder().isError(false).dataList(assignments).total(assignments.size()).build();
        }
        catch (Exception e){
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse findById(Integer id) {
        try{
            Optional<Assignment> assignment = assignmentRepository.findById(id);
            if(assignment.isEmpty()) {
                return  BaseResponse.simpleFail(String.format("Assignment id: %d does not exist", id));
            }
            return DataResponse.builder()
                    .isError(false)
                    .data(this.mapToAssignmentDTO(assignment.get()))
                    .build();
        }
        catch (Exception e){
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(CreateAssignmentDTO createAssignmentDTO) {
        try {
            createAssignmentDTO.setUpdatedAt(LocalDateTime.now());
            createAssignmentDTO.setCreatedAt(LocalDateTime.now());
            AssignmentDTO assignmentDTO = new AssignmentDTO();
            BeanUtils.copyProperties(createAssignmentDTO, assignmentDTO);
            Assignment newAssignment = mapToAssignment( assignmentDTO);
            assignmentRepository.save(newAssignment);
            return DataResponse.simpleSuccess("New assignment created successfully");
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse update(UpdateAssignmentDTO updateAssignmentDTO) {
        try {
            int assignmentId = updateAssignmentDTO.getId();
            updateAssignmentDTO.setUpdatedAt(LocalDateTime.now());
            Optional<Assignment> existingAssignmentOptional = assignmentRepository.findById(assignmentId);
            if (existingAssignmentOptional.isPresent()) {
                Assignment existingAssignment = existingAssignmentOptional.get();
                AssignmentDTO assignmentDTO = new AssignmentDTO();
                BeanUtils.copyProperties(updateAssignmentDTO, assignmentDTO);
                Assignment updatedAssignment = mapToAssignment(assignmentDTO);
                BeanUtils.copyProperties(updatedAssignment, existingAssignment, UtilsFunctions.getNullPropertyNames(updatedAssignment));
                Assignment savedAssignment = assignmentRepository.save(existingAssignment);
                mapToAssignmentDTO(savedAssignment);
                return DataResponse.simpleSuccess("Assignment updated successfully");
            } else {
                return BaseResponse.simpleFail(String.format("Assignment id: %d does not exist", assignmentId));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse delete(Integer id) {
        try {
            Optional<Assignment> assignmentOptional = assignmentRepository.findById(id);

            if (assignmentOptional.isPresent()) {
                assignmentRepository.deleteById(id);
                return BaseResponse.simpleSuccess("Assignment deleted successfully");
            } else {
                return BaseResponse.simpleFail(String.format("Assignment id: %d does not exist", id));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }
    public BaseResponse deleteMultiple(List<Integer> ids){
        try {
            if (ids.isEmpty()) {
                return BaseResponse.simpleFail("No Assignment ids provided for deletion");
            }
            List<Assignment> assignmentsToDelete = assignmentRepository.findAllByIdIn(ids);
            if (assignmentsToDelete.isEmpty()) {
                return BaseResponse.simpleFail("No matching Assignment ids found");
            }
            List<Integer> deletedAssignmentIds = assignmentsToDelete.stream()
                    .map(Assignment::getId)
                    .collect(Collectors.toList());
            assignmentRepository.deleteAllInBatch(assignmentsToDelete);

            return DataResponse.builder()
                    .isError(false)
                    .message("Assignments deleted successfully")
                    .data(deletedAssignmentIds)
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }


    private AssignmentDTO mapToAssignmentDTO(Assignment assignment) {
            AssignmentDTO assignmentDTO = new AssignmentDTO();
            assignmentDTO.setId(assignment.getId());
            assignmentDTO.setDescription(assignment.getDescription());
            assignmentDTO.setType(assignment.getType());
            assignmentDTO.setCourseID(assignment.getCourse().getId());
            assignmentDTO.setCreatedAt(assignment.getCreatedAt());
            assignmentDTO.setUpdatedAt(assignment.getUpdatedAt());
            assignmentDTO.setAttachedFilesUrl(assignment.getAttachedFilesUrl());
            return assignmentDTO;
    }
    private Assignment mapToAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        assignment.setId(assignmentDTO.getId());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setType(assignmentDTO.getType());
//        courseRepository.findById(assignmentDTO.getCourseID())
//                .ifPresent(assignment::setCourse);
        Integer courseId = assignmentDTO.getCourseID();
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) assignment.setCourse(courseOptional.get());
        else {
            throw new UdemyValidateException("Invalid Course ID");
        }

        assignment.setCreatedAt(assignmentDTO.getCreatedAt());
        assignment.setUpdatedAt(assignmentDTO.getUpdatedAt());
        assignment.setAttachedFilesUrl(assignmentDTO.getAttachedFilesUrl());
        return assignment;
    }
}
