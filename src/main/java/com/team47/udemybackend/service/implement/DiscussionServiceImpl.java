package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.dto.DiscussionDTO;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.models.Discussion;
import com.team47.udemybackend.models.Lecture;
import com.team47.udemybackend.repository.DiscussionRepository;
import com.team47.udemybackend.repository.LectureRepository;
import com.team47.udemybackend.service.DiscussionService;
import com.team47.udemybackend.user.User;
import com.team47.udemybackend.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Data
@Transactional
public class DiscussionServiceImpl implements DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    public DiscussionServiceImpl(DiscussionRepository discussionRepository, UserRepository userRepository, LectureRepository lectureRepository) {
        this.discussionRepository = discussionRepository;
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
    }

    @Override
    public DiscussionDTO findDiscussionsByLectureIdAndUserId(Integer lectureID, Integer userID) {
        return mapToDisDTO(discussionRepository.findDiscussionsByLectureIdAndUserId(lectureID, userID));
    }

    @Override
    public DiscussionDTO deleteDiscussionById(Integer discussionID) {
        return mapToDisDTO(discussionRepository.deleteDiscussionById(discussionID));
    }

    @Override
    public DiscussionDTO updateDiscussionById(Integer discussionID, DiscussionDTO discussionDTO) {
        Discussion discussion = discussionRepository.findDiscussionsById(discussionID);
        discussion.setComment(discussionDTO.getComment());
        discussion.setUpdatedAt(discussionDTO.getCreatedAt());
        discussionRepository.save(discussion);
        return mapToDisDTO(discussion);
    }

    @Override
    public DiscussionDTO findDiscussionsById(Integer discussionID) {
        return mapToDisDTO(discussionRepository.findDiscussionsById(discussionID));
    }

    @Override
    public DiscussionDTO createDiscussion(Integer lectureID, Integer userID, DiscussionDTO discussionDTO) throws UserNotFoundException {
        Optional<Lecture> result = lectureRepository.findById(lectureID);
        Lecture lecture;
        if (result.isPresent()) {
            lecture = result.get();
        } else {
            throw new RuntimeException("Lecture Not Found");
        }
        Optional<User> users = userRepository.findById(userID);
        User user;
        if (users.isPresent()) {
            user = users.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
        Discussion discussion = Discussion.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .comment(discussionDTO.getComment())
                .lecture(lecture)
                .user(user)
                .build();
        return mapToDisDTO(discussion);
    }

    @Override
    public DiscussionDTO deleteByLectureIdAndUserId(Integer lectureID, Integer userID) {
        Discussion discussion = discussionRepository.deleteDiscussionByLectureIdAndUserId(lectureID, userID);
        return mapToDisDTO(discussion);
    }
    public DiscussionDTO mapToDisDTO(Discussion discussion){
        return DiscussionDTO.builder()
                .id(discussion.getId())
                .comment(discussion.getComment())
                .createdAt(discussion.getCreatedAt())
                .updatedAt(discussion.getUpdatedAt())
                .build();
    }
    public Discussion mapToDis(DiscussionDTO discussionDTO){
        return Discussion.builder()
                .id(discussionDTO.getId())
                .comment(discussionDTO.getComment())
                .createdAt(discussionDTO.getCreatedAt())
                .updatedAt(discussionDTO.getUpdatedAt())
                .build();
    }
}
