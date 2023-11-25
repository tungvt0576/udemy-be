package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.models.Course;

import java.util.List;

public interface CourseService {
    public List<CourseDTO> findAll();
    public CourseDTO findById(Integer id) throws CourseNotFoundException;
    public List<CourseDTO> listAllByKeyword(String searchTitle);
    public CourseDTO createNew(Course course);
    public CourseDTO updateInfoById(CourseDTO courseDTO, Integer id) throws CourseNotFoundException;

    public void delete(Integer id) throws CourseNotFoundException;
}
