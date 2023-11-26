package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.feedback.CreateFeedbackDTO;
import com.team47.udemybackend.dto.feedback.UpdateFeedbackDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.service.FeedbackService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("${api.prefix}/feedback")
public class FeedbackController {

    @Resource
    FeedbackService feedbackService;

    @GetMapping("/")
    public ResponseEntity<DataListResponse> listAll() {
        return new ResponseEntity<>(feedbackService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<DataListResponse> getAllPage(
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNum,
            @RequestParam(name = "course_id", required = false) Integer courseID,
            @RequestParam(name = "user_id", required = false) Integer userID
    ) {
        return new ResponseEntity<>(feedbackService.listByFilter(pageSize, pageNum, courseID, userID), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(feedbackService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse> createFeedback(@RequestBody CreateFeedbackDTO feedbackDTO) {
        return new ResponseEntity<>(feedbackService.create(feedbackDTO), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<BaseResponse> updateFeedback(@RequestBody UpdateFeedbackDTO feedbackDTO) {
        return new ResponseEntity<>(feedbackService.update(feedbackDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteFeedback(@PathVariable Integer id) {
        return new ResponseEntity<>(feedbackService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<BaseResponse> deleteFeedbacks(
            @RequestBody List<Integer> idList) {
        return new ResponseEntity<>(feedbackService.deleteMultiple(idList), HttpStatus.OK);
    }
}
