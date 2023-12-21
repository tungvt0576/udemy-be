package com.team47.udemybackend.controllers;

import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.exception.EnrollNotFoundException;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.models.Enroll;
import com.team47.udemybackend.service.EnrollService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Data
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class EnrollController {
    private final EnrollService enrollService;

    public EnrollController(EnrollService enrollService) {
        this.enrollService = enrollService;
    }

    @GetMapping("enroll/{enrollID}")
    public ResponseEntity<Enroll> findEnrollByID(@PathVariable Integer enrollID) throws EnrollNotFoundException {
        return new ResponseEntity<>(enrollService.findEnrollByID(enrollID), HttpStatus.OK);
    }

    @GetMapping("enroll/user/{userID}")
    public ResponseEntity<Set<Enroll>> findEnrollByUserId(@PathVariable Integer userID) throws EnrollNotFoundException {
        return new ResponseEntity<>(enrollService.findAllEnrollByUserID(userID), HttpStatus.OK);
    }

    @GetMapping("enroll/course/{courseID}")
    public ResponseEntity<Set<Enroll>> findEnrollByCourseId(@PathVariable Integer courseID) throws EnrollNotFoundException {
        return new ResponseEntity<>(enrollService.findAllEnrollByCourseId(courseID), HttpStatus.OK);
    }

    @PostMapping("enroll/create/{courseID}/{userID}")
    public ResponseEntity<Enroll> createNewEnroll(@PathVariable Integer courseID, @PathVariable Integer userID) throws UserNotFoundException, CourseNotFoundException {
        return new ResponseEntity<>(enrollService.createEnroll(courseID, userID), HttpStatus.OK);
    }

    @DeleteMapping("enroll/delete/{enrollID}")
    public ResponseEntity<String> deleteEnroll(@PathVariable Integer enrollID) {
        enrollService.deleteEnrollByID(enrollID);
        return new ResponseEntity<>("Deleted enroll", HttpStatus.OK);
    }
}
