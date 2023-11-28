package com.team47.udemybackend.service;

import com.team47.udemybackend.dto.ChangeMoneyRequest;
import com.team47.udemybackend.dto.ChangePasswordRequest;
import com.team47.udemybackend.dto.UserDTO;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.user.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<UserDTO> findAll();

    UserDTO findUserById(Integer userId) throws UserNotFoundException;

    UserDTO findUserByEmail(String email) throws UserNotFoundException;

    void changePassWord(ChangePasswordRequest request, Principal connectedUser) throws IllegalAccessException;

    void changeMoney(ChangeMoneyRequest amountChanged, Integer userID) throws UserNotFoundException;

    void delete(Integer userId);

    UserDTO updatedByID(UserDTO userDTO, Integer userID) throws UserNotFoundException;

    User findUserByIDHelper(Integer userID) throws UserNotFoundException;
//    public List<Course> findCourseByEnrolledCourses(Integer userId) throws UserNotFoundException;
}
