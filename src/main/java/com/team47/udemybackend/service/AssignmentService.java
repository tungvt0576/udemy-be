package com.team47.udemybackend.service;

import com.team47.udemybackend.models.Assignment;
import org.springframework.data.domain.Page;

public interface AssignmentService {
    Page<Assignment> listAll();

}
