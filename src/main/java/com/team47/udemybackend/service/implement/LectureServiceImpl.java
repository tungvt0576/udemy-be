package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.dto.lecture.CreateLectureDTO;
import com.team47.udemybackend.dto.lecture.LectureDTO;
import com.team47.udemybackend.dto.lecture.UpdateLectureDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.dto.response.DataResponse;
import com.team47.udemybackend.exception.UdemyRuntimeException;
import com.team47.udemybackend.exception.UdemyValidateException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.models.Lecture;
import com.team47.udemybackend.models.Section;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.repository.LectureRepository;
import com.team47.udemybackend.repository.SectionRepository;
import com.team47.udemybackend.service.LectureService;
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
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public DataListResponse listByFilter(int pageSize, int pageNum, Integer courseID, Integer sectionID, String name) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());

            Specification<Lecture> specification = Specification.where(null);

            if (courseID != null) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("course").get("id"), courseID));
            }

            if (sectionID != null) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("section").get("id"), sectionID));
            }

            if (name != null && !name.isEmpty()) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            Page<Lecture> lecturePage = lectureRepository.findAll(specification, pageRequest);

            List<LectureDTO> lectureDTOs = lecturePage.getContent().stream()
                    .map(this::mapToLectureDTO)
                    .collect(Collectors.toList());

            return DataListResponse.builder()
                    .isError(false)
                    .dataList(lectureDTOs)
                    .total(lecturePage.getTotalElements())
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
            List<LectureDTO> lectures = new ArrayList<>();
            lectureRepository.findAll().forEach(lecture -> lectures.add(mapToLectureDTO(lecture)));
            return DataListResponse.builder().isError(false).dataList(lectures).total(lectures.size()).build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse findById(Integer id) {
        try {
            Optional<Lecture> lectureOptional = lectureRepository.findById(id);

            if (lectureOptional.isPresent()) {
                LectureDTO lectureDTO = mapToLectureDTO(lectureOptional.get());
                return DataResponse.builder()
                        .isError(false)
                        .data(lectureDTO)
                        .build();
            } else {
                return BaseResponse.simpleFail(String.format("Lecture id: %d does not exist", id));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(CreateLectureDTO createLectureDTO) {
        try {
            createLectureDTO.setUpdatedAt(LocalDateTime.now());
            createLectureDTO.setCreatedAt(LocalDateTime.now());
            LectureDTO lectureDTO = new LectureDTO();
            BeanUtils.copyProperties(createLectureDTO, lectureDTO);

            Lecture newLecture = mapToLecture(lectureDTO);
            lectureRepository.save(newLecture);

            return DataResponse.simpleSuccess("New lecture created successfully");
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse update(UpdateLectureDTO updateLectureDTO) {
        try {
            int lectureId = updateLectureDTO.getId();
            updateLectureDTO.setUpdatedAt(LocalDateTime.now());
            Optional<Lecture> existingLectureOptional = lectureRepository.findById(lectureId);

            if (existingLectureOptional.isPresent()) {
                Lecture existingLecture = existingLectureOptional.get();
                LectureDTO lectureDTO = new LectureDTO();
                BeanUtils.copyProperties(updateLectureDTO, lectureDTO);

                Lecture updatedLecture = mapToLecture(lectureDTO);
                BeanUtils.copyProperties(updatedLecture, existingLecture, UtilsFunctions.getNullPropertyNames(updatedLecture));

                Lecture savedLecture = lectureRepository.save(existingLecture);
                mapToLectureDTO(savedLecture);

                return DataResponse.simpleSuccess("Lecture updated successfully");
            } else {
                return BaseResponse.simpleFail(String.format("Lecture id: %d does not exist", lectureId));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse delete(int id) {
        try {
            Optional<Lecture> lectureOptional = lectureRepository.findById(id);

            if (lectureOptional.isPresent()) {
                lectureRepository.deleteById(id);
                return BaseResponse.simpleSuccess("Lecture deleted successfully");
            } else {
                return BaseResponse.simpleFail(String.format("Lecture id: %d does not exist", id));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteMultiple(List<Integer> ids) {
        try {
            if (ids.isEmpty()) {
                return BaseResponse.simpleFail("No Lecture ids provided for deletion");
            }

            List<Lecture> lecturesToDelete = lectureRepository.findAllById(ids);

            if (lecturesToDelete.isEmpty()) {
                return BaseResponse.simpleFail("No matching Lecture ids found");
            }

            List<Integer> deletedLectureIds = lecturesToDelete.stream()
                    .map(Lecture::getId)
                    .collect(Collectors.toList());

            lectureRepository.deleteAllInBatch(lecturesToDelete);

            return DataResponse.builder()
                    .isError(false)
                    .message("Lectures deleted successfully")
                    .data(deletedLectureIds)
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    private LectureDTO mapToLectureDTO(Lecture lecture) {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setId(lecture.getId());
        lectureDTO.setName(lecture.getName());
        lectureDTO.setVideoUrl(lecture.getVideoUrl());
        lectureDTO.setCourseID(lecture.getCourse().getId());
        lectureDTO.setSectionID(lecture.getSection().getId());
        lectureDTO.setCreatedAt(lecture.getCreatedAt());
        lectureDTO.setUpdatedAt(lecture.getUpdatedAt());
        return lectureDTO;
    }

    private Lecture mapToLecture(LectureDTO lectureDTO) {
        Lecture lecture = new Lecture();
        lecture.setId(lectureDTO.getId());
        lecture.setName(lectureDTO.getName());
        lecture.setVideoUrl(lectureDTO.getVideoUrl());

        // Set Course
        Integer courseId = lectureDTO.getCourseID();
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            lecture.setCourse(courseOptional.get());
        } else {
            throw new UdemyValidateException("Invalid Course ID");
        }

        // Set Section
        Integer sectionId = lectureDTO.getSectionID();
        Optional<Section> sectionOptional = sectionRepository.findById(sectionId);
        if (sectionOptional.isPresent()) {
            lecture.setSection(sectionOptional.get());
        } else {
            throw new UdemyValidateException("Invalid Section ID");
        }

        lecture.setCreatedAt(lectureDTO.getCreatedAt());
        lecture.setUpdatedAt(lectureDTO.getUpdatedAt());
        return lecture;
    }
}

