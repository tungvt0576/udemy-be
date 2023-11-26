package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.AssignmentsSubmission;
import com.team47.udemybackend.models.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    Page<Feedback> findAll(Specification<Feedback> specification, Pageable pageRequest);
}
