package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.dto.ChangeMoneyRequest;
import com.team47.udemybackend.dto.ChangePasswordRequest;
import com.team47.udemybackend.dto.UserDTO;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.models.Enroll;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.repository.EnrollRepository;
import com.team47.udemybackend.user.UserRepository;
import com.team47.udemybackend.service.UserService;
import com.team47.udemybackend.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final EnrollRepository enrollRepository;


    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::mapToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(Integer userId) throws UserNotFoundException {
        User user = findUserByIDHelper(userId);
        return mapToUserDTO(user);
    }

    @Override
    public UserDTO findUserByEmail(String email) throws UserNotFoundException {
        Optional<User> result = Optional.ofNullable(userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(String.format("User %s not found", email))));
        if (result.isPresent()) {
            return mapToUserDTO(result.get());
        } else {
            throw new UserNotFoundException(String.format("User %s not found", email));
        }
    }

    @Override
    public void changePassWord(ChangePasswordRequest request, Principal connectedUser) throws IllegalAccessException {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        //Check ìf current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalAccessException("Password is not correct!");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalAccessException("Passwords are not matched");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void changeMoney(ChangeMoneyRequest amountChanged, Integer userID) throws UserNotFoundException {
        User user = findUserByIDHelper(userID);
        user.setMoney(user.getMoney() - amountChanged.getChangeAmount());
        userRepository.save(user);
    }

    @Override
    public void delete(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO updatedByID(UserDTO userDTO, Integer userID) throws UserNotFoundException {
        User user = findUserByIDHelper(userID);
        user.setName(userDTO.getName());
        user.setAvatar(userDTO.getAvatar());
        user.setEmail(userDTO.getEmail());
        user.setWebsite(userDTO.getWebsite());
        user.setDescription(userDTO.getDescription());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());
        user.setMoney(userDTO.getMoney());
        user.setRole(userDTO.getRole());
        return mapToUserDTO(user);
    }

    @Override
    public User findUserByIDHelper(Integer userID) throws UserNotFoundException {
        Optional<User> result = Optional.ofNullable(userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException("User not found")));
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new UserNotFoundException(String.format("User ID: %d not found", userID));
        }
    }
    @Override
    public List<UserDTO> findUsersByEnrolledCourses(Integer courseId) throws UserNotFoundException {
        List<Enroll> enrolls = enrollRepository.findEnrollsByCourseId(courseId).stream().toList();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(Enroll enrollment : enrolls){
            userDTOS.add(mapToUserDTO(enrollment.getUser()));
        }
        return userDTOS;
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setEmail(user.getEmail());
        userDTO.setWebsite(user.getWebsite());
        userDTO.setDescription(user.getDescription());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(LocalDateTime.now());
        userDTO.setMoney(user.getMoney());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
    private User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAvatar(userDTO.getAvatar());
        user.setEmail(userDTO.getEmail());
        user.setWebsite(userDTO.getWebsite());
        user.setDescription(userDTO.getDescription());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setMoney(userDTO.getMoney());
        return user;
    }
}
