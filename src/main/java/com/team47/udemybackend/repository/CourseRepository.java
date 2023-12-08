package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByTitleContainingOrCategoryContaining(String title, String category);

    List<Course> findCoursesByUserEmail(String email);

    List<Course> findCoursesByUserId(Integer id);

}

