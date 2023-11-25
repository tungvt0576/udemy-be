package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer>{
    List<Course> findByTitleContainingOrCategoryContaining(String title, String category);

}

