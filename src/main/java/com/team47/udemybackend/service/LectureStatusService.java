package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.lecture.CreateLectureDTO;
import com.team47.udemybackend.dto.lecture.UpdateLectureDTO;
import com.team47.udemybackend.dto.lecture_status.CreateLectureStatusDTO;
import com.team47.udemybackend.dto.lecture_status.UpdateLectureStatusDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;

import java.util.List;

public interface LectureStatusService {
    DataListResponse listByFilter(int pageSize, int pageNum, Integer lectureID, Integer userID);
    DataListResponse listAll();
    BaseResponse findById(Integer id);
    BaseResponse create(CreateLectureStatusDTO createLectureStatusDTO);
    BaseResponse update(UpdateLectureStatusDTO updateLectureStatusDTO) ;
    BaseResponse delete(int id);
    BaseResponse deleteMultiple(List<Integer> ids);
}
