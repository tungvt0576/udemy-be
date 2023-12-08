package com.team47.udemybackend.service;

import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.user.User;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface AdminService {
    public Course deleteCourseByID(Integer courseId);
    public User deleteUserByID(Integer userId);
    public Course acceptCourseById(Integer courseId);
}
