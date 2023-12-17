package com.team47.udemybackend.service;

import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.exception.EnrollNotFoundException;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.models.Enroll;

import java.util.Set;

public interface EnrollService {
    Set<Enroll> findAllEnrollByCourseId(Integer courseID) throws EnrollNotFoundException;

    Set<Enroll> findAllEnrollByUserID(Integer userID) throws EnrollNotFoundException;

    Enroll findEnrollByID(Integer enrollID) throws EnrollNotFoundException;

    void deleteEnrollByID(Integer enrollID);

    Enroll createEnroll(Integer courseID, Integer UserID) throws UserNotFoundException, CourseNotFoundException;
//    public Enroll updateEnroll(Enroll enroll, Integer enrollID);
}
