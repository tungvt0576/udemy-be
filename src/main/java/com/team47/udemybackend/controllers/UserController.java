package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.ChangeMoneyRequest;
import com.team47.udemybackend.dto.ChangePasswordRequest;
import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.dto.UserDTO;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.service.UserService;
import com.team47.udemybackend.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("api/v1")
@CrossOrigin
public class UserController {
    @Autowired
    private final UserService userService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<UserDTO> findByID(@PathVariable Integer userID) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findUserById(userID), HttpStatus.OK);
    }
    @GetMapping("/user/enrolled/{courseId}")
    public ResponseEntity<List<UserDTO>> findUserEnrolledCourse(@PathVariable Integer courseId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findUsersByEnrolledCourses(courseId),HttpStatus.OK);
    }

    @GetMapping("/user/email")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/user/enroll/list/{courseID}")
    public ResponseEntity<List<UserDTO>> findEnrolledUserByCourse(@PathVariable Integer courseID) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findUsersByEnrolledCourses(courseID), HttpStatus.OK);
    }
    @PutMapping("/user/update/{userID}")
    public ResponseEntity<UserDTO> updateInfo(@PathVariable Integer userID, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updatedByID(userDTO, userID), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userID) {
        userService.delete(userID);
        return new ResponseEntity<>("deleted user", HttpStatus.OK);
    }

    @PutMapping("user/update_role/{userID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateRoleById(@PathVariable Integer userID, @RequestParam String role) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateRoleById(userID, role), HttpStatus.OK);
    }

    @PatchMapping("/user/update_pass")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) throws IllegalAccessException {
        userService.changePassWord(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/money/{userID}")
    public ResponseEntity<?> updateMoney(@RequestBody ChangeMoneyRequest changedAmount, @PathVariable Integer userID) throws UserNotFoundException {
        userService.changeMoney(changedAmount, userID);
        return ResponseEntity.ok().build();
    }
}
