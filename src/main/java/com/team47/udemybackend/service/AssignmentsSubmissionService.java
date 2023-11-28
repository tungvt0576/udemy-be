package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.assignment.CreateAssignmentDTO;
import com.team47.udemybackend.dto.assignment.UpdateAssignmentDTO;
import com.team47.udemybackend.dto.assignmentssubmission.CreateAssignmentsSubmissionDTO;
import com.team47.udemybackend.dto.assignmentssubmission.UpdateAssignmentsSubmissionDTO;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.dto.response.DataListResponse;

import java.util.List;

public interface AssignmentsSubmissionService {
    DataListResponse listByFilter(int pageSize, int pageNum, Integer assignmentID, Integer userID);
    DataListResponse listAll();
    BaseResponse findById(Integer id);
    BaseResponse create(CreateAssignmentsSubmissionDTO createAssignmentsSubmissionDTO);
    BaseResponse update(UpdateAssignmentsSubmissionDTO updateAssignmentsSubmissionDTO) ;
    BaseResponse delete(int id);
    BaseResponse deleteMultiple(List<Integer> ids);
}
