package com.team47.udemybackend.repository;

import com.team47.udemybackend.dto.DiscussionDTO;
import com.team47.udemybackend.models.Discussion;
import com.team47.udemybackend.user.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Integer> {
    public Discussion findDiscussionsByLectureIdAndUserId(Integer lectureID, Integer userID);
    public Discussion deleteDiscussionById(Integer discussionID);
    public Discussion findDiscussionsById(Integer discussionID);
    public Discussion deleteDiscussionByLectureIdAndUserId(Integer lectureID, Integer userID);
}
