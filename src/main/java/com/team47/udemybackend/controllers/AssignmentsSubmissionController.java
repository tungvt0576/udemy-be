package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.assignmentssubmission.CreateAssignmentsSubmissionDTO;
import com.team47.udemybackend.dto.assignmentssubmission.UpdateAssignmentsSubmissionDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.service.AssignmentsSubmissionService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("${api.prefix}/assignments-submission")
public class AssignmentsSubmissionController {
    @Resource
    AssignmentsSubmissionService assignmentsSubmissionService;

    @GetMapping()
    public ResponseEntity<DataListResponse> listAll() {
        return new ResponseEntity<>(assignmentsSubmissionService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<DataListResponse> getAllPage(
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNum,
            @RequestParam(name = "assignment_id", required = false) Integer assignmentID,
            @RequestParam(name = "user_id", required = false) Integer userID
    ) {
        return new ResponseEntity<>(assignmentsSubmissionService.listByFilter(pageSize, pageNum, assignmentID, userID), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(assignmentsSubmissionService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> createAssignmentSubmission(@RequestBody CreateAssignmentsSubmissionDTO submissionDTO) {
        return new ResponseEntity<>(assignmentsSubmissionService.create(submissionDTO), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<BaseResponse> updateAssignmentSubmission(@RequestBody UpdateAssignmentsSubmissionDTO submissionDTO) {
        return new ResponseEntity<>(assignmentsSubmissionService.update(submissionDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteAssignmentSubmission(@PathVariable Integer id) {
        return new ResponseEntity<>(assignmentsSubmissionService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<BaseResponse> deleteAssignmentSubmissions(
            @RequestBody List<Integer> idList) {
        return new ResponseEntity<>(assignmentsSubmissionService.deleteMultiple(idList), HttpStatus.OK);
    }
}
