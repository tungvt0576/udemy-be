package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.assignment.AssignmentDTO;
import com.team47.udemybackend.dto.assignment.CreateAssignmentDTO;
import com.team47.udemybackend.dto.assignment.UpdateAssignmentDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;

import java.util.List;

public interface AssignmentService {
    DataListResponse listByFilter(int pageSize, int pageNum, Integer courseID, String type);
    DataListResponse listAll();
    BaseResponse findById(Integer id);
    BaseResponse create(CreateAssignmentDTO createAssignmentDTO);
    BaseResponse update(UpdateAssignmentDTO updateAssignmentDTO) ;
    BaseResponse delete(Integer id);
    BaseResponse deleteMultiple(List<Integer> ids);
}
