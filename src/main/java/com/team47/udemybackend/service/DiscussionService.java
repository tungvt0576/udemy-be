package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.DiscussionDTO;
import com.team47.udemybackend.exception.DiscussionNotFoundException;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.models.Discussion;
import com.team47.udemybackend.repository.DiscussionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

public interface DiscussionService {
    public DiscussionDTO findDiscussionsByLectureIdAndUserId(Integer lectureID, Integer userID) throws DiscussionNotFoundException;
    public DiscussionDTO deleteDiscussionById(Integer discussionID) throws DiscussionNotFoundException;
    public DiscussionDTO updateDiscussionById(Integer discussionID, DiscussionDTO discussionDTO) throws DiscussionNotFoundException;
    public DiscussionDTO findDiscussionsById(Integer discussionID) throws DiscussionNotFoundException;
    public DiscussionDTO createDiscussion(Integer lectureID, Integer userID, DiscussionDTO discussionDTO) throws UserNotFoundException;
    public DiscussionDTO deleteByLectureIdAndUserId(Integer lectureID, Integer userID) throws DiscussionNotFoundException;
}
