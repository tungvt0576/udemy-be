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
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
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

    @Autowired
    public EnrollServiceImpl(EnrollRepository enrollRepository, CourseService courseService, CourseRepository courseRepository, UserService userService) {
        this.enrollRepository = enrollRepository;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        this.userService = userService;
    }


    @Override
    public Set<Enroll> findAllEnrollByCourseId(Integer courseID) {
        return enrollRepository.findEnrollsByCourseId(courseID);
    }

    @Override
    public Set<Enroll> findAllEnrollByUserID(Integer userID) {
        return enrollRepository.findEnrollsByUserId(userID);
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
        if(enrollRepository.findEnrollsByCourseIdAndUserId(courseID, userID) == null) {
            Enroll enroll = new Enroll();
            Course course = courseService.findCourseByIDHelper(courseID);
            enroll.setUser(userService.findUserByIDHelper(userID));
            enroll.setCreatedAt(LocalDateTime.now());
            enroll.setUpdatedAt(LocalDateTime.now());

            course.setTotalEnroll(course.getTotalEnroll() + 1);
            courseRepository.save(course);
            enroll.setCourse(course);
            enrollRepository.save(enroll);
            return enroll;
        }else{
            throw new RuntimeException("Enroll already exist");
        }
    }
}
