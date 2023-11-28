package com.team47.udemybackend.repository;


import com.team47.udemybackend.models.AssignmentsSubmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentsSubmissionRepository extends JpaRepository<AssignmentsSubmission,Integer> {
    List<AssignmentsSubmission> findByAssignment_IdAndUser_Id(Integer assignmentId, Integer userId);
    Page<AssignmentsSubmission>  findAll(Specification<AssignmentsSubmission>  specification, Pageable pageRequest);
}
