package com.team47.udemybackend.controllers;

import com.team47.udemybackend.models.Assignment;
import com.team47.udemybackend.repository.AssignmentRepository;
import com.team47.udemybackend.service.AssignmentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("${api.prefix}/assignment")
public class AssignmentController {
    @Autowired
    AssignmentRepository assignmentRepository;
    @Resource
    AssignmentService assignmentService;
    @GetMapping("/list")
    public ResponseEntity<List<Assignment>> getAll(){
        try {
            List<Assignment> assignments= new ArrayList<Assignment>();

            assignmentRepository.findAll().forEach(assignments::add);
            if (assignments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(assignments, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/list-page")
    public ResponseEntity<Page<Assignment>> getAllPage(){
        try {
            Page<Assignment> assignments= assignmentService.listAll();
            if (assignments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(assignments, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
