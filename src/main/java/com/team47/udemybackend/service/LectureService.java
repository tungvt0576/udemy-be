package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.feedback.CreateFeedbackDTO;
import com.team47.udemybackend.dto.feedback.UpdateFeedbackDTO;
import com.team47.udemybackend.dto.lecture.CreateLectureDTO;
import com.team47.udemybackend.dto.lecture.UpdateLectureDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;

import java.util.List;

public interface LectureService {
    DataListResponse listByFilter(int pageSize, int pageNum, Integer courseID, Integer sectionID, String name);
    DataListResponse listAll();
    BaseResponse findById(Integer id);
    BaseResponse create(CreateLectureDTO createLectureDTO);
    BaseResponse update(UpdateLectureDTO updateLectureDTO) ;
    BaseResponse delete(int id);
    BaseResponse deleteMultiple(List<Integer> ids);
}
