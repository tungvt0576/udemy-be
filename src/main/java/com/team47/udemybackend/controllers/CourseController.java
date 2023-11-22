package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CourseController {
    private CourseService courseService;
    @GetMapping("/course")
    public List<CourseDTO> findAll(){
        return courseService.findAll();
    }
    @GetMapping("/course/{courseID}")
    public CourseDTO findById(@PathVariable Long courseID){
        return courseService.findById(courseID);
    }
    @GetMapping("/course/{keyword}")
    public List<CourseDTO> findAllByKeyword(@PathVariable String keyword){
        return courseService.listAll(keyword);
    }
    @PostMapping("/course")
    public CourseDTO addNewCourse(@RequestBody Course course){
        return courseService.createNew(course);
    }
    @PutMapping("/course/{courseID}")
    public CourseDTO updateById(@PathVariable Long courseID) throws CourseNotFoundException {
        return courseService.updateInfoById(courseID);
    }
    @DeleteMapping("/course/{courseId}")
    public void deleteById(@PathVariable Long courseId) throws CourseNotFoundException {
        courseService.delete(courseId);
    }
}
