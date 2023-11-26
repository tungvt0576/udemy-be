package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.assignment.CreateAssignmentDTO;
import com.team47.udemybackend.dto.assignment.UpdateAssignmentDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.service.AssignmentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("${api.prefix}/assignment")
public class AssignmentController {
    @Resource
    AssignmentService assignmentService;
    @GetMapping()
    public ResponseEntity<BaseResponse> listAll(){
        return new ResponseEntity<>(assignmentService.listAll(), HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity<DataListResponse> getAllPage(
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNum,
            @RequestParam(name = "course_id", required = false) Integer courseID,
            @RequestParam(name = "type", required = false) String type
    ) {
        return new ResponseEntity<>(assignmentService.listByFilter(pageSize, pageNum, courseID, type), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(assignmentService.findById(id), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<BaseResponse> createAssignment(@RequestBody CreateAssignmentDTO assignmentDTO) {
        return new ResponseEntity<>(assignmentService.create(assignmentDTO), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<BaseResponse> updateAssignment(@RequestBody UpdateAssignmentDTO assignmentDTO) {
        return new ResponseEntity<>(assignmentService.update(assignmentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteAssignment(@PathVariable Integer id) {
        return new ResponseEntity<>(assignmentService.delete(id), HttpStatus.OK);
    }
    @DeleteMapping()
    public ResponseEntity<BaseResponse> deleteAssignments(
            @RequestBody List<Integer> idList) {
        return new ResponseEntity<>(assignmentService.deleteMultiple(idList), HttpStatus.OK);
    }

}
