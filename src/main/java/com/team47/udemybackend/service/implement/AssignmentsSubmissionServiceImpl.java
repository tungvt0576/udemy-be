package com.team47.udemybackend.service.implement;
import com.team47.udemybackend.dto.assignmentssubmission.AssignmentsSubmissionDTO;
import com.team47.udemybackend.dto.assignmentssubmission.CreateAssignmentsSubmissionDTO;
import com.team47.udemybackend.dto.assignmentssubmission.UpdateAssignmentsSubmissionDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.dto.response.DataResponse;
import com.team47.udemybackend.exception.UdemyRuntimeException;
import com.team47.udemybackend.exception.UdemyValidateException;
import com.team47.udemybackend.models.Assignment;
import com.team47.udemybackend.models.AssignmentsSubmission;
import com.team47.udemybackend.repository.AssignmentRepository;
import com.team47.udemybackend.repository.AssignmentsSubmissionRepository;
import com.team47.udemybackend.user.UserRepository;
import com.team47.udemybackend.service.AssignmentsSubmissionService;
import com.team47.udemybackend.user.User;
import com.team47.udemybackend.utils.UtilsFunctions;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AssignmentsSubmissionServiceImpl implements AssignmentsSubmissionService {
    @Autowired
    private AssignmentsSubmissionRepository assignmentsSubmissionRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public DataListResponse listByFilter(int pageSize, int pageNum, Integer assignmentID, Integer userID) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());

            Specification<AssignmentsSubmission> specification = Specification.where(null);
            if (assignmentID != null) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("assignment").get("id"), assignmentID));
            }
            if (userID != null) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("user").get("id"), userID));
            }

            Page<AssignmentsSubmission> assignmentSubmissionsPage = assignmentsSubmissionRepository.findAll(specification, pageRequest);

            List<AssignmentsSubmissionDTO> assignmentSubmissionDTOs = assignmentSubmissionsPage.getContent().stream()
                    .map(this::mapToAssignmentsSubmissionDTO)
                    .collect(Collectors.toList());

            return DataListResponse.builder()
                    .isError(false)
                    .dataList(assignmentSubmissionDTOs)
                    .total(assignmentSubmissionsPage.getTotalElements())
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
            List<AssignmentsSubmissionDTO> assignmentSubmissionDTOs = new ArrayList<>();
            assignmentsSubmissionRepository.findAll().forEach(submission ->
                    assignmentSubmissionDTOs.add(mapToAssignmentsSubmissionDTO(submission)));
            return DataListResponse.builder()
                    .isError(false)
                    .dataList(assignmentSubmissionDTOs)
                    .total(assignmentSubmissionDTOs.size())
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse findById(Integer id) {
        try {
            Optional<AssignmentsSubmission> assignmentSubmissionOptional = assignmentsSubmissionRepository.findById(id);

            if (assignmentSubmissionOptional.isPresent()) {
                AssignmentsSubmissionDTO assignmentSubmissionDTO = mapToAssignmentsSubmissionDTO(assignmentSubmissionOptional.get());
                return DataResponse.builder()
                        .isError(false)
                        .data(assignmentSubmissionDTO)
                        .build();
            } else {
                return BaseResponse.simpleFail(String.format("AssignmentsSubmission id: %d does not exist", id));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(CreateAssignmentsSubmissionDTO createAssignmentsSubmissionDTO) {
        try {
            AssignmentsSubmission newSubmission = mapToAssignmentsSubmission(createAssignmentsSubmissionDTO);
//            assignmentsSubmissionRepository.save(newSubmission);
//
//            return DataResponse.simpleSuccess("New assignment submission created successfully");
            return DataResponse.builder()
                    .isError(false)
                    .data(assignmentsSubmissionRepository.save(newSubmission))
                    .message("New assignment submission created successfully")
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse update(UpdateAssignmentsSubmissionDTO updateAssignmentsSubmissionDTO) {
        try {

            int submissionId = updateAssignmentsSubmissionDTO.getId();
            Optional<AssignmentsSubmission> existingSubmissionOptional = assignmentsSubmissionRepository.findById(submissionId);

            if (existingSubmissionOptional.isPresent()) {
                AssignmentsSubmission existingSubmission = existingSubmissionOptional.get();
                AssignmentsSubmission updatedSubmission = mapToAssignmentsSubmission(updateAssignmentsSubmissionDTO);

                BeanUtils.copyProperties(updatedSubmission, existingSubmission, UtilsFunctions.getNullPropertyNames(updatedSubmission));


                AssignmentsSubmission savedSubmission = assignmentsSubmissionRepository.save(existingSubmission);

                AssignmentsSubmissionDTO savedSubmissionDTO = mapToAssignmentsSubmissionDTO(savedSubmission);
                return DataResponse.builder()
                        .isError(false)
                        .data(savedSubmissionDTO)
                        .message("AssignmentsSubmission updated successfully")
                        .build();
            } else {

                return BaseResponse.simpleFail(String.format("AssignmentsSubmission id: %d does not exist", submissionId));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse delete(int id) {
        try {

            Optional<AssignmentsSubmission> submissionOptional = assignmentsSubmissionRepository.findById(id);

            if (submissionOptional.isPresent()) {

                assignmentsSubmissionRepository.deleteById(id);
                return BaseResponse.simpleSuccess("AssignmentsSubmission deleted successfully");
            } else {

                return BaseResponse.simpleFail(String.format("AssignmentsSubmission id: %d does not exist", id));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteMultiple(List<Integer> ids) {
        try {
            if (ids.isEmpty()) {
                return BaseResponse.simpleFail("No AssignmentsSubmission ids provided for deletion");
            }

            List<AssignmentsSubmission> submissionsToDelete = assignmentsSubmissionRepository.findAllById(ids);

            if (submissionsToDelete.isEmpty()) {

                return BaseResponse.simpleFail("No matching AssignmentsSubmission ids found");
            }


            List<Integer> deletedSubmissionIds = submissionsToDelete.stream()
                    .map(AssignmentsSubmission::getId)
                    .collect(Collectors.toList());
            assignmentsSubmissionRepository.deleteAllInBatch(submissionsToDelete);

            return DataResponse.builder()
                    .isError(false)
                    .message("AssignmentsSubmissions deleted successfully")
                    .data(deletedSubmissionIds)
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }


    private AssignmentsSubmissionDTO mapToAssignmentsSubmissionDTO(AssignmentsSubmission submission) {
        return AssignmentsSubmissionDTO.builder()
                .id(submission.getId())
                .assignmentID(submission.getAssignment().getId())
                .userID(submission.getUser().getId())
                .createdAt(submission.getCreatedAt())
                .updatedAt(submission.getUpdatedAt())
                .build();
    }

    private AssignmentsSubmission mapToAssignmentsSubmission(CreateAssignmentsSubmissionDTO createAssignmentsSubmissionDTO) {
        AssignmentsSubmission submission = new AssignmentsSubmission();
        submission.setCreatedAt(LocalDateTime.now());
        submission.setUpdatedAt(LocalDateTime.now());
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(createAssignmentsSubmissionDTO.getAssignmentID());
        if (assignmentOptional.isPresent()) {
            submission.setAssignment(assignmentOptional.get());
        } else {
            throw new UdemyValidateException("Invalid Assignment ID");
        }

        Optional<User> userOptional = userRepository.findById(createAssignmentsSubmissionDTO.getUserID());
        if (userOptional.isPresent()) {
            submission.setUser(userOptional.get());
        } else {
            throw new UdemyValidateException("Invalid User ID");
        }

        return submission;
    }

    private AssignmentsSubmission mapToAssignmentsSubmission(UpdateAssignmentsSubmissionDTO updateAssignmentsSubmissionDTO) {
        AssignmentsSubmission submission = new AssignmentsSubmission();
        submission.setId(updateAssignmentsSubmissionDTO.getId());
        submission.setUpdatedAt(LocalDateTime.now());

        Optional<Assignment> assignmentOptional = assignmentRepository.findById(updateAssignmentsSubmissionDTO.getAssignmentID());
        if (assignmentOptional.isPresent()) {
            submission.setAssignment(assignmentOptional.get());
        } else {
            throw new UdemyValidateException("Invalid Assignment ID");
        }

        Optional<User> userOptional = userRepository.findById(updateAssignmentsSubmissionDTO.getUserID());
        if (userOptional.isPresent()) {
            submission.setUser(userOptional.get());
        } else {
            throw new UdemyValidateException("Invalid User ID");
        }

        return submission;
    }
}
