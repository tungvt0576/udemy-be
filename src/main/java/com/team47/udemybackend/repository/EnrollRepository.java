package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Integer> {
    Set<Enroll> findEnrollsByUserId(Integer userID);

    Set<Enroll> findEnrollsByCourseId(Integer courseID);

    Enroll findEnrollsByCourseIdAndUserId(Integer courseID, Integer userID);

}
