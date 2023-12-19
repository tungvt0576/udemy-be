package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.dto.feedback.CreateFeedbackDTO;
import com.team47.udemybackend.dto.feedback.FeedbackDTO;
import com.team47.udemybackend.dto.feedback.UpdateFeedbackDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.dto.response.DataResponse;
import com.team47.udemybackend.exception.UdemyRuntimeException;
import com.team47.udemybackend.exception.UdemyValidateException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.models.Feedback;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.repository.FeedbackRepository;
import com.team47.udemybackend.user.UserRepository;
import com.team47.udemybackend.service.FeedbackService;
import com.team47.udemybackend.user.User;
import com.team47.udemybackend.utils.AuthTokenUtil;
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
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public DataListResponse listByFilter(int pageSize, int pageNum, Integer courseID, Integer userID) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());

            Specification<Feedback> specification = Specification.where(null);
            if (courseID != null) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("course").get("id"), courseID));
            }
            if (userID != null) {
                specification = specification.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("user").get("id"), userID));
            }

            Page<Feedback> feedbackPage = feedbackRepository.findAll(specification, pageRequest);

            List<FeedbackDTO> feedbackDTOs = feedbackPage.getContent().stream()
                    .map(this::mapToFeedbackDTO)
                    .collect(Collectors.toList());

            return DataListResponse.builder()
                    .isError(false)
                    .dataList(feedbackDTOs)
                    .total(feedbackPage.getTotalElements())
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
            List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
            feedbackRepository.findAll().forEach(feedback ->
                    feedbackDTOs.add(mapToFeedbackDTO(feedback)));
            return DataListResponse.builder()
                    .isError(false)
                    .dataList(feedbackDTOs)
                    .total(feedbackDTOs.size())
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse findById(Integer id) {
        try {
            Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);

            if (feedbackOptional.isPresent()) {
                FeedbackDTO feedbackDTO = mapToFeedbackDTO(feedbackOptional.get());
                return DataResponse.builder()
                        .isError(false)
                        .data(feedbackDTO)
                        .build();
            } else {
                return BaseResponse.simpleFail(String.format("Feedback id: %d does not exist", id));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(CreateFeedbackDTO createFeedbackDTO) {
        try {
            int currentUserID = AuthTokenUtil.getCurrentUserId();
            createFeedbackDTO.setUserID(currentUserID);
            Feedback newFeedback = mapToFeedback(createFeedbackDTO);
            feedbackRepository.save(newFeedback);

            return DataResponse.simpleSuccess("New feedback created successfully");
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse update(UpdateFeedbackDTO updateFeedbackDTO) {
        try {
            int currentUserID = AuthTokenUtil.getCurrentUserId();
            updateFeedbackDTO.setUserID(currentUserID);
            int feedbackId = updateFeedbackDTO.getId();
            Optional<Feedback> existingFeedbackOptional = feedbackRepository.findById(feedbackId);

            if (existingFeedbackOptional.isPresent()) {
                Feedback existingFeedback = existingFeedbackOptional.get();
                Feedback updatedFeedback = mapToFeedback(updateFeedbackDTO);

                BeanUtils.copyProperties(updatedFeedback, existingFeedback, UtilsFunctions.getNullPropertyNames(updatedFeedback));

                Feedback savedFeedback = feedbackRepository.save(existingFeedback);

                FeedbackDTO savedFeedbackDTO = mapToFeedbackDTO(savedFeedback);
                return DataResponse.builder()
                        .isError(false)
                        .data(savedFeedbackDTO)
                        .message("Feedback updated successfully")
                        .build();
            } else {
                return BaseResponse.simpleFail(String.format("Feedback id: %d does not exist", feedbackId));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse delete(int id) {
        try {
            Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);

            if (feedbackOptional.isPresent()) {
                feedbackRepository.deleteById(id);
                return BaseResponse.simpleSuccess("Feedback deleted successfully");
            } else {
                return BaseResponse.simpleFail(String.format("Feedback id: %d does not exist", id));
            }
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteMultiple(List<Integer> ids) {
        try {
            if (ids.isEmpty()) {
                return BaseResponse.simpleFail("No Feedback ids provided for deletion");
            }

            List<Feedback> feedbacksToDelete = feedbackRepository.findAllById(ids);

            if (feedbacksToDelete.isEmpty()) {
                return BaseResponse.simpleFail("No matching Feedback ids found");
            }

            List<Integer> deletedFeedbackIds = feedbacksToDelete.stream()
                    .map(Feedback::getId)
                    .collect(Collectors.toList());
            feedbackRepository.deleteAllInBatch(feedbacksToDelete);

            return DataResponse.builder()
                    .isError(false)
                    .message("Feedbacks deleted successfully")
                    .data(deletedFeedbackIds)
                    .build();
        } catch (Exception e) {
            throw new UdemyRuntimeException(e.getMessage());
        }
    }

    private FeedbackDTO mapToFeedbackDTO(Feedback feedback) {
        return FeedbackDTO.builder()
                .id(feedback.getId())
                .courseID(feedback.getCourse().getId())
                .userID(feedback.getUser().getId())
                .rating(feedback.getRating())
                .feedBack(feedback.getFeedBack())
                .time(feedback.getTime())
                .createdAt(feedback.getCreatedAt())
                .updatedAt(feedback.getUpdatedAt())
                .build();
    }

    private Feedback mapToFeedback(CreateFeedbackDTO createFeedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setUpdatedAt(LocalDateTime.now());

        Optional<Course> courseOptional = courseRepository.findById(createFeedbackDTO.getCourseID());
        if (courseOptional.isPresent()) {
            feedback.setCourse(courseOptional.get());
        } else {
            throw new UdemyValidateException("Invalid Course ID");
        }

        Optional<User> userOptional = userRepository.findById(createFeedbackDTO.getUserID());
        if (userOptional.isPresent()) {
            feedback.setUser(userOptional.get());
        } else {
            throw new UdemyValidateException("Invalid User ID");
        }

        feedback.setRating(createFeedbackDTO.getRating());
        feedback.setFeedBack(createFeedbackDTO.getFeedBack());
        feedback.setTime(createFeedbackDTO.getTime());

        return feedback;
    }

    private Feedback mapToFeedback(UpdateFeedbackDTO updateFeedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setId(updateFeedbackDTO.getId());
        feedback.setUpdatedAt(LocalDateTime.now());

        Optional<Course> courseOptional = courseRepository.findById(updateFeedbackDTO.getCourseID());
        if (courseOptional.isPresent()) {
            feedback.setCourse(courseOptional.get());
        } else {
            throw new UdemyValidateException("Invalid Course ID");
        }

        Optional<User> userOptional = userRepository.findById(updateFeedbackDTO.getUserID());
        if (userOptional.isPresent()) {
            feedback.setUser(userOptional.get());
        } else {
            throw new UdemyValidateException("Invalid User ID");
        }

        feedback.setRating(updateFeedbackDTO.getRating());
        feedback.setFeedBack(updateFeedbackDTO.getFeedBack());
        feedback.setTime(updateFeedbackDTO.getTime());

        return feedback;
    }
}
