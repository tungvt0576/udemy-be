package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.lecture_status.CreateLectureStatusDTO;
import com.team47.udemybackend.dto.lecture_status.UpdateLectureStatusDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.service.LectureStatusService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("${api.prefix}/lecture-status")
@CrossOrigin
public class LectureStatusController {

    @Resource
    LectureStatusService lectureStatusService;

    @GetMapping()
    public ResponseEntity<DataListResponse> listAll() {
        return new ResponseEntity<>(lectureStatusService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<DataListResponse> getAllPage(
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNum,
            @RequestParam(name = "lecture_id", required = false) Integer lectureID,
            @RequestParam(name = "user_id", required = false) Integer userID
    ) {
        return new ResponseEntity<>(lectureStatusService.listByFilter(pageSize, pageNum, lectureID, userID), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(lectureStatusService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> createLectureStatus(@RequestBody CreateLectureStatusDTO lectureStatusDTO) {
        return new ResponseEntity<>(lectureStatusService.create(lectureStatusDTO), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<BaseResponse> updateLectureStatus(@RequestBody UpdateLectureStatusDTO lectureStatusDTO) {
        return new ResponseEntity<>(lectureStatusService.update(lectureStatusDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteLectureStatus(@PathVariable Integer id) {
        return new ResponseEntity<>(lectureStatusService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<BaseResponse> deleteLectureStatuses(
            @RequestBody List<Integer> idList) {
        return new ResponseEntity<>(lectureStatusService.deleteMultiple(idList), HttpStatus.OK);
    }
}
