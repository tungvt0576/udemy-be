package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {
    Page<Assignment> findByTypeAndCourse_Id(String type, Integer courseId, Pageable pageable);

    Page<Assignment> findByCourse_Id(Integer courseId, Pageable pageable);

    Page<Assignment> findByType(String type, PageRequest pageRequest);

    List<Assignment> findAllByIdIn(List<Integer> ids);
}
