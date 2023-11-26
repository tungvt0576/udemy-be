package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.feedback.CreateFeedbackDTO;
import com.team47.udemybackend.dto.feedback.UpdateFeedbackDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;

import java.util.List;

public interface FeedbackService {
    DataListResponse listByFilter(int pageSize, int pageNum, Integer courseID, Integer userID);
    DataListResponse listAll();
    BaseResponse findById(Integer id);
    BaseResponse create(CreateFeedbackDTO createFeedbackDTO);
    BaseResponse update(UpdateFeedbackDTO updateFeedbackDTO) ;
    BaseResponse delete(int id);
    BaseResponse deleteMultiple(List<Integer> ids);
}
