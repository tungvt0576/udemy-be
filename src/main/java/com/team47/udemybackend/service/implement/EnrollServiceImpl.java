package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.exception.EnrollNotFoundException;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.models.Enroll;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.repository.EnrollRepository;
import com.team47.udemybackend.service.CourseService;
import com.team47.udemybackend.service.EnrollService;
import com.team47.udemybackend.service.UserService;
import com.team47.udemybackend.user.User;
import com.team47.udemybackend.user.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Data
@Service
@Transactional
public class EnrollServiceImpl implements EnrollService {
    private final EnrollRepository enrollRepository;
    private final CourseService courseService;
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public EnrollServiceImpl(EnrollRepository enrollRepository, CourseService courseService, CourseRepository courseRepository, UserService userService, UserRepository userRepository) {
        this.enrollRepository = enrollRepository;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @Override
    public Set<Enroll> findAllEnrollByCourseId(Integer courseID) throws EnrollNotFoundException {
        Set<Enroll> enrolls = enrollRepository.findEnrollsByCourseId(courseID);
        if(enrolls != null){
            return enrolls;
        }else {
            throw new EnrollNotFoundException("Enroll not found");
        }
    }

    @Override
    public Set<Enroll> findAllEnrollByUserID(Integer userID) throws EnrollNotFoundException {
        Set<Enroll> enrolls = enrollRepository.findEnrollsByUserId(userID);
        if(enrolls != null){
            return enrolls;
        }else {
            throw new EnrollNotFoundException("Enroll not found");
        }
    }

    @Override
    public Enroll findEnrollByID(Integer enrollID) throws EnrollNotFoundException {
        Optional<Enroll> result = enrollRepository.findById(enrollID);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EnrollNotFoundException("Enroll not found");
        }
    }

    @Override
    public void deleteEnrollByID(Integer enrollID) {
        enrollRepository.deleteById(enrollID);
    }

    @Override
    public Enroll createEnroll(Integer courseID, Integer userID) throws UserNotFoundException, CourseNotFoundException {
        if (enrollRepository.findEnrollsByCourseIdAndUserId(courseID, userID) == null) {
            Enroll enroll = new Enroll();
            Course course = courseService.findCourseByIDHelper(courseID);
            User user = userService.findUserByIDHelper(userID);
            if(user.getMoney() >= course.getPrice()){
                user.setMoney(user.getMoney() - course.getPrice());
//                userService.updatedByID(userService.mapToUserDTO(user), userID);
                userRepository.save(user);
                enroll.setUser(user);
                enroll.setType("Full time");
                enroll.setCreatedAt(LocalDateTime.now());
                enroll.setUpdatedAt(LocalDateTime.now());

                course.setTotalEnroll(course.getTotalEnroll() + 1);
                courseRepository.save(course);
                enroll.setCourse(course);
                enrollRepository.save(enroll);
                return enroll;
            }else {
                throw new RuntimeException("Your remaining amount is not enough to buy the course");
            }
        } else {
            throw new RuntimeException("Enroll already exist");
        }
    }
}
