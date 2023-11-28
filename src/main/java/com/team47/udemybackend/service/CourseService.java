package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.models.Course;

import java.security.Principal;
import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();

    CourseDTO findById(Integer id) throws CourseNotFoundException;

    List<CourseDTO> listAllByKeyword(String searchTitle) throws CourseNotFoundException;

    CourseDTO createNew(Course course, Principal connectedUser);

    CourseDTO updateInfoById(CourseDTO courseDTO, Integer id) throws CourseNotFoundException;

    void delete(Integer id) throws CourseNotFoundException;

    List<CourseDTO> findCourseCreatedByEmail(String email) throws CourseNotFoundException;

    List<CourseDTO> findCourseCreatedById(Integer id) throws CourseNotFoundException;

    Course findCourseByIDHelper(Integer courseID) throws CourseNotFoundException;
//    public List<User> findUserByEnrolledCourse(Integer courseId) throws CourseNotFoundException;
}
