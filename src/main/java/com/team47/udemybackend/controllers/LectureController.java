package com.team47.udemybackend.controllers;

import com.team47.udemybackend.dto.lecture.CreateLectureDTO;
import com.team47.udemybackend.dto.lecture.UpdateLectureDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;
import com.team47.udemybackend.service.LectureService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("${api.prefix}/lecture")
@CrossOrigin
public class LectureController {

    @Resource
    LectureService lectureService;

    @GetMapping()
    public ResponseEntity<DataListResponse> listAll() {
        return new ResponseEntity<>(lectureService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<DataListResponse> getAllPage(
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "page_number", required = false, defaultValue = "0") int pageNum,
            @RequestParam(name = "course_id", required = false) Integer courseID,
            @RequestParam(name = "section_id", required = false) Integer sectionID,
            @RequestParam(name = "name", required = false) String name
    ) {
        return new ResponseEntity<>(lectureService.listByFilter(pageSize, pageNum, courseID, sectionID, name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(lectureService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> createLecture(@RequestBody CreateLectureDTO lectureDTO) {
        return new ResponseEntity<>(lectureService.create(lectureDTO), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<BaseResponse> updateLecture(@RequestBody UpdateLectureDTO lectureDTO) {
        return new ResponseEntity<>(lectureService.update(lectureDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteLecture(@PathVariable Integer id) {
        return new ResponseEntity<>(lectureService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<BaseResponse> deleteLectures(
            @RequestBody List<Integer> idList) {
        return new ResponseEntity<>(lectureService.deleteMultiple(idList), HttpStatus.OK);
    }
}
