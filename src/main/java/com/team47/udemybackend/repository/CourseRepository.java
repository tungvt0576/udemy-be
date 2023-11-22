package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>{
    @Query("SELECT course FROM Course course WHERE CONCAT(course.title, ' ', course.courseDescription, ' ', course.subtitle) LIKE %?1%")
    List<Course> search(String keyword);
}
