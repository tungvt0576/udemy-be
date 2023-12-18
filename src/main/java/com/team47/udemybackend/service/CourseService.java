package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.user.User;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface CourseService {
    List<CourseDTO> findAll();

    CourseDTO findById(Integer id) throws CourseNotFoundException;

    List<CourseDTO> listAllByKeyword(String searchTitle) throws CourseNotFoundException;

    Course createNew(CourseDTO courseDTO, Principal connectedUser);

    CourseDTO updateInfoById(CourseDTO courseDTO, Integer id) throws CourseNotFoundException;

    void delete(Integer id) throws CourseNotFoundException;

    List<CourseDTO> findCourseCreatedByEmail(String email) throws CourseNotFoundException;

    List<CourseDTO> findCourseCreatedById(Integer id) throws CourseNotFoundException;

    Course findCourseByIDHelper(Integer courseID) throws CourseNotFoundException;
    List<CourseDTO> findCoursesByEnrolledUser(Integer userId) throws CourseNotFoundException, UserNotFoundException;

//    List<CourseDTO> findCoursesByEnrolledUser(Set<User> user) throws CourseNotFoundException;
//    public List<User> findUserByEnrolledCourse(Integer courseId) throws CourseNotFoundException;
}
