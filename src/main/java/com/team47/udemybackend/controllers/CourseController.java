package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CourseController {
    Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;
    @GetMapping("/course")
    public List<CourseDTO> findAll(){
        return courseService.findAll();
    }
    @GetMapping("/course/{courseID}")
    public ResponseEntity<CourseDTO> findById(@PathVariable Integer courseID) throws CourseNotFoundException {
        return new ResponseEntity<>(courseService.findById(courseID), HttpStatus.OK);
    }
    @GetMapping("/course/search")
    public ResponseEntity<List<CourseDTO>> findAllByTitle(@RequestParam(value = "keyword") String keyword) {
        return new ResponseEntity<>(courseService.listAllByKeyword(keyword), HttpStatus.OK);
    }
    @PostMapping("/course")
    public ResponseEntity<CourseDTO> addNewCourse(@RequestBody Course course){
        return new ResponseEntity<>(courseService.createNew(course),HttpStatus.OK);
    }
    @PutMapping("/course/{courseID}")
    public ResponseEntity<CourseDTO> updateById(@RequestBody CourseDTO courseDTO, @PathVariable Integer courseID) throws CourseNotFoundException {
        ResponseEntity<CourseDTO> ResponseEntity;
        return new ResponseEntity<>(courseService.updateInfoById(courseDTO, courseID), HttpStatus.OK);
    }
    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<String> deleteById(@PathVariable Integer courseId) throws CourseNotFoundException {
        courseService.delete(courseId);
        return new ResponseEntity<>("Course deleted", HttpStatus.OK);
    }
}
