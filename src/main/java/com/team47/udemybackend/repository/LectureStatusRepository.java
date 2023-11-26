package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.Lecture;
import com.team47.udemybackend.models.LectureStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureStatusRepository extends JpaRepository<LectureStatus, Integer> {
    Page<LectureStatus> findAll(Specification<LectureStatus> specification, Pageable pageRequest);
}
