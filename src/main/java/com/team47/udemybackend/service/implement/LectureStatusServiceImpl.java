package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.dto.lecture_status.CreateLectureStatusDTO;
import com.team47.udemybackend.dto.lecture_status.LectureStatusDTO;
import com.team47.udemybackend.dto.lecture_status.UpdateLectureStatusDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.dto.response.DataResponse;
import com.team47.udemybackend.exception.UdemyRuntimeException;
import com.team47.udemybackend.exception.UdemyValidateException;
import com.team47.udemybackend.models.Lecture;
import com.team47.udemybackend.models.LectureStatus;
import com.team47.udemybackend.repository.LectureRepository;
import com.team47.udemybackend.repository.LectureStatusRepository;
import com.team47.udemybackend.user.UserRepository;
import com.team47.udemybackend.service.LectureStatusService;
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
public class LectureStatusServiceImpl implements LectureStatusService {

    @Autowired
    private LectureStatusRepository lectureStatusRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public DataListResponse listByFilter(int pageSize, int pageNum, Integer lectureID, Integer userID) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());

            Specification<LectureStatus> specification = Specification.where(null);

            if (lectureID != null) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("lecture").get("id"), lectureID));
            }

            if (userID != null) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("user").get("id"), userID));
            }

            Page<LectureStatus> lectureStatusPage = lectureStatusRepository.findAll(specification, pageRequest);

            List<LectureStatusDTO> lectureStatusDTOs = lectureStatusPage.getContent().stream()
                    .map(this::mapToLectureStatusDTO)
                    .collect(Collectors.toList());

            return DataListResponse.builder()
                    .isError(false)
                    .dataList(lectureStatusDTOs)
                    .total(lectureStatusPage.getTotalElements())
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
            List<LectureStatusDTO> lectureStatuses = new ArrayList<>();
            lectureStatusRepository.findAll().forEach(lectureStatus -> lectureStatuses.add(mapToLectureStatusDTO(lectureStatus)));
            return DataListResponse.builder().isError(false).dataList(lectureStatuses).total(lectureStatuses.size()).build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse findById(Integer id) {
        try {
            Optional<LectureStatus> lectureStatusOptional = lectureStatusRepository.findById(id);

            if (lectureStatusOptional.isPresent()) {
                LectureStatusDTO lectureStatusDTO = mapToLectureStatusDTO(lectureStatusOptional.get());
                return DataResponse.builder()
                        .isError(false)
                        .data(lectureStatusDTO)
                        .build();
            } else {
                return BaseResponse.simpleFail(String.format("LectureStatus id: %d does not exist", id));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(CreateLectureStatusDTO createLectureStatusDTO) {
        try {
            createLectureStatusDTO.setUpdatedAt(LocalDateTime.now());
            createLectureStatusDTO.setCreatedAt(LocalDateTime.now());
            LectureStatusDTO lectureStatusDTO = new LectureStatusDTO();
            BeanUtils.copyProperties(createLectureStatusDTO, lectureStatusDTO);

            LectureStatus newLectureStatus = mapToLectureStatus(lectureStatusDTO);
//            lectureStatusRepository.save(newLectureStatus);

//            return DataResponse.simpleSuccess("New lecture status created successfully");
            return DataResponse.builder()
                    .isError(false)
                    .data(lectureStatusRepository.save(newLectureStatus))
                    .message("New lecture status created successfully")
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse update(UpdateLectureStatusDTO updateLectureStatusDTO) {
        try {
            int lectureStatusId = updateLectureStatusDTO.getId();
            updateLectureStatusDTO.setUpdatedAt(LocalDateTime.now());
            Optional<LectureStatus> existingLectureStatusOptional = lectureStatusRepository.findById(lectureStatusId);

            if (existingLectureStatusOptional.isPresent()) {
                LectureStatus existingLectureStatus = existingLectureStatusOptional.get();
                LectureStatusDTO lectureStatusDTO = new LectureStatusDTO();
                BeanUtils.copyProperties(updateLectureStatusDTO, lectureStatusDTO);

                LectureStatus updatedLectureStatus = mapToLectureStatus(lectureStatusDTO);
                BeanUtils.copyProperties(updatedLectureStatus, existingLectureStatus, UtilsFunctions.getNullPropertyNames(updatedLectureStatus));

                LectureStatus savedLectureStatus = lectureStatusRepository.save(existingLectureStatus);
                mapToLectureStatusDTO(savedLectureStatus);

                return DataResponse.simpleSuccess("Lecture status updated successfully");
            } else {
                return BaseResponse.simpleFail(String.format("LectureStatus id: %d does not exist", lectureStatusId));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse delete(int id) {
        try {
            Optional<LectureStatus> lectureStatusOptional = lectureStatusRepository.findById(id);

            if (lectureStatusOptional.isPresent()) {
                lectureStatusRepository.deleteById(id);
                return BaseResponse.simpleSuccess("Lecture status deleted successfully");
            } else {
                return BaseResponse.simpleFail(String.format("LectureStatus id: %d does not exist", id));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteMultiple(List<Integer> ids) {
        try {
            if (ids.isEmpty()) {
                return BaseResponse.simpleFail("No LectureStatus ids provided for deletion");
            }

            List<LectureStatus> lectureStatusesToDelete = lectureStatusRepository.findAllById(ids);

            if (lectureStatusesToDelete.isEmpty()) {
                return BaseResponse.simpleFail("No matching LectureStatus ids found");
            }

            List<Integer> deletedLectureStatusIds = lectureStatusesToDelete.stream()
                    .map(LectureStatus::getId)
                    .collect(Collectors.toList());

            lectureStatusRepository.deleteInBatch(lectureStatusesToDelete);

            return DataResponse.builder()
                    .isError(false)
                    .message("Lecture statuses deleted successfully")
                    .data(deletedLectureStatusIds)
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    private LectureStatusDTO mapToLectureStatusDTO(LectureStatus lectureStatus) {
        LectureStatusDTO lectureStatusDTO = new LectureStatusDTO();
        lectureStatusDTO.setId(lectureStatus.getId());
        lectureStatusDTO.setStatus(lectureStatus.isStatus());
        lectureStatusDTO.setLectureID(lectureStatus.getLecture().getId());
        lectureStatusDTO.setUserID(lectureStatus.getUser().getId());
        lectureStatusDTO.setCreatedAt(lectureStatus.getCreatedAt());
        lectureStatusDTO.setUpdatedAt(lectureStatus.getUpdatedAt());
        return lectureStatusDTO;
    }

    private LectureStatus mapToLectureStatus(LectureStatusDTO lectureStatusDTO) {
        LectureStatus lectureStatus = new LectureStatus();
        lectureStatus.setId(lectureStatusDTO.getId());
        lectureStatus.setStatus(lectureStatusDTO.isStatus());

        Integer lectureId = lectureStatusDTO.getLectureID();
        Optional<Lecture> lectureOptional = lectureRepository.findById(lectureId);
        if (lectureOptional.isPresent()) {
            lectureStatus.setLecture(lectureOptional.get());
        } else {
            throw new UdemyValidateException("Invalid Lecture ID");
        }

        Integer userId = lectureStatusDTO.getUserID();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            lectureStatus.setUser(userOptional.get());
        } else {
            throw new UdemyValidateException("Invalid User ID");
        }

        lectureStatus.setCreatedAt(lectureStatusDTO.getCreatedAt());
        lectureStatus.setUpdatedAt(lectureStatusDTO.getUpdatedAt());
        return lectureStatus;
    }
}


