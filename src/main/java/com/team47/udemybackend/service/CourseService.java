package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    public List<CourseDTO> findAll();
    public CourseDTO findById(Long id);
    public List<CourseDTO> listAll(String searchTitle);
    public CourseDTO createNew(Course course);
    public CourseDTO updateInfoById(Long id) throws CourseNotFoundException;
    public void delete(Long id) throws CourseNotFoundException;
}
