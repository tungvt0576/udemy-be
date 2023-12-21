package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.dto.UserDTO;
import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.service.CourseService;
import com.team47.udemybackend.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@Data
@CrossOrigin
public class AdminController {

    private final UserService userService;
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    public AdminController(UserService userService, CourseService courseService, CourseRepository courseRepository) {
        this.userService = userService;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin:read')")
    @Hidden
    public ResponseEntity<List<UserDTO>> getALl(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    @Hidden
    public ResponseEntity<String> deleteAccountByID(@PathVariable Integer userId){
        userService.delete(userId);
        return new ResponseEntity<>(String.format("Deleted user id : %d", userId),HttpStatus.OK);
    }
    @PutMapping("/course/{courseId}")
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public ResponseEntity<String> acceptCourse(@PathVariable  Integer courseId) throws CourseNotFoundException {
        Course course = courseService.findCourseByIDHelper(courseId);
        course.setStatus("active");
        courseRepository.save(course);
        return new ResponseEntity<>(String.format("Accepted course : %s", course.getTitle()), HttpStatus.OK);
    }

    @DeleteMapping("/course/{courseId}")
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public ResponseEntity<String> deleteCourse(@PathVariable Integer courseId){
        courseRepository.deleteById(courseId);
        return new ResponseEntity<>(String.format("Deleted course: %d", courseId), HttpStatus.OK);
    }
}
