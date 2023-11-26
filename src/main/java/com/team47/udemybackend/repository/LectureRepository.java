package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.AssignmentsSubmission;
import com.team47.udemybackend.models.Feedback;
import com.team47.udemybackend.models.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    Page<Lecture> findAll(Specification<Lecture> specification, Pageable pageRequest);
}
