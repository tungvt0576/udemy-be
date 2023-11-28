package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.models.Assignment;
import com.team47.udemybackend.repository.AssignmentRepository;
import com.team47.udemybackend.service.AssignmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public Page<Assignment> listAll() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Page<Assignment> assignments1 = assignmentRepository.findAll(firstPageWithTwoElements);
        return assignments1;
    }
}
