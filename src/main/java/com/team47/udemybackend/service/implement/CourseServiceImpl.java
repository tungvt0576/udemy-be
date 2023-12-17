package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.dto.UserDTO;
import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.exception.UserNotFoundException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.models.Enroll;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.repository.EnrollRepository;
import com.team47.udemybackend.service.UserService;
import com.team47.udemybackend.user.UserRepository;
import com.team47.udemybackend.service.CourseService;
import com.team47.udemybackend.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@Data
@NoArgsConstructor
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private EnrollRepository enrollRepository;
    private UserService userService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, EnrollRepository enrollRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.enrollRepository = enrollRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream().map(this::mapToCourseDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> listAllByKeyword(String keyword) throws CourseNotFoundException {
        try {
            if (keyword != null) {
                return courseRepository.findByTitleContainingOrCategoryContaining(keyword, keyword).stream().map(this::mapToCourseDTO).collect(Collectors.toList());
            }
            return courseRepository.findAll().stream().map(this::mapToCourseDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CourseNotFoundException("Server Error");
        }
    }

    @Override
    public CourseDTO findById(Integer Id) throws CourseNotFoundException {
        Optional<Course> result = Optional.ofNullable(courseRepository.findById(Id).orElseThrow(() -> new CourseNotFoundException("Course not found")));

        if (result.isPresent()) {
            Course course = result.get();
            return mapToCourseDTO(course);
        } else {
            throw new RuntimeException(String.format("Course Id: %d does not exist", Id));
        }
    }

    @Override
    public CourseDTO createNew(Course course, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        course.setUser(user);
        Course course_ = courseRepository.save(course);
        return mapToCourseDTO(course_);
    }

    @Override
    public CourseDTO updateInfoById(CourseDTO courseDTO, Integer id) throws CourseNotFoundException {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course could not be found"));

        course.setCourseFor(courseDTO.getCourseFor());
        course.setCourseDescription((courseDTO.getCourseDescription()));
        course.setCourseImageUrl(courseDTO.getCourseImageUrl());
        course.setId(courseDTO.getId());
        course.setCategory(courseDTO.getCategory());
        course.setLanguage(courseDTO.getLanguage());
        course.setLevel(courseDTO.getLevel());
        course.setPrice(courseDTO.getPrice());
        course.setRating(courseDTO.getRating());
        course.setCreatedAt(courseDTO.getCreatedAt());
        course.setId(courseDTO.getId());
        course.setSale(courseDTO.getSale());
        course.setWelcomeMessage(courseDTO.getWelcomeMessage());
        course.setCongratulationMessage(courseDTO.getCongratulationMessage());
        course.setSubtitle(courseDTO.getSubtitle());
        course.setStatus(courseDTO.getStatus());
        course.setUpdatedAt(LocalDateTime.now());
        course.setLearningObject(courseDTO.getLearningObject());
        course.setPrimarilyTaught(courseDTO.getPrimarilyTaught());

        Course course_ = courseRepository.save(course);
        return mapToCourseDTO(course_);
    }

    @Override
    public void delete(Integer id) throws CourseNotFoundException {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course could not be found"));
        courseRepository.delete(course);
    }

    @Override
    public List<CourseDTO> findCourseCreatedByEmail(String userName) throws CourseNotFoundException {
        List<Course> result = courseRepository.findCoursesByUserEmail(userName);
        if (result != null) {
            return result.stream().map(this::mapToCourseDTO).collect(Collectors.toList());
        } else {
            throw new CourseNotFoundException("No course created");
        }
    }

    @Override
    public List<CourseDTO> findCourseCreatedById(Integer id) throws CourseNotFoundException {
        List<Course> result = courseRepository.findCoursesByUserId(id);
        if (result != null) {
            return result.stream().map(this::mapToCourseDTO).collect(Collectors.toList());
        } else {
            throw new CourseNotFoundException("No course already enrolled");
        }
    }

    @Override
    public Course findCourseByIDHelper(Integer courseID) throws CourseNotFoundException {
        Optional<Course> result = courseRepository.findById(courseID);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new CourseNotFoundException("No course already enrolled");
        }
    }

    @Override
    public List<CourseDTO> findCoursesByEnrolledUser(Integer userId) throws CourseNotFoundException, UserNotFoundException {
        List<Enroll> enrolls = enrollRepository.findEnrollsByUserId(userId).stream().toList();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for(Enroll enrollment : enrolls){
            courseDTOS.add(mapToCourseDTO(enrollment.getCourse()));
        }

        return courseDTOS;
    }

    public CourseDTO mapToCourseDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseFor(course.getCourseFor());
        courseDTO.setCourseDescription((course.getCourseDescription()));
        courseDTO.setCourseImageUrl(course.getCourseImageUrl());
        courseDTO.setId(course.getId());
        courseDTO.setCategory(course.getCategory());
        courseDTO.setLanguage(course.getLanguage());
        courseDTO.setLevel(course.getLevel());
        courseDTO.setPrice(course.getPrice());
        courseDTO.setRating(course.getRating());
        courseDTO.setCreatedAt(course.getCreatedAt());
        courseDTO.setId(course.getId());
        courseDTO.setSale(course.getSale());
        courseDTO.setWelcomeMessage(course.getWelcomeMessage());
        courseDTO.setCongratulationMessage(course.getCongratulationMessage());
        courseDTO.setSubtitle(course.getSubtitle());
        courseDTO.setStatus(course.getStatus());
        courseDTO.setUpdatedAt(LocalDateTime.now());
        courseDTO.setLearningObject(course.getLearningObject());
        courseDTO.setPrimarilyTaught(course.getPrimarilyTaught());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setRequiredSkills(course.getRequiredSkills());
        courseDTO.setTotalEnroll(course.getTotalEnroll());
        return courseDTO;
    }

    public Course mapToCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseFor(courseDTO.getCourseFor());
        course.setTitle(courseDTO.getTitle());
        course.setRequiredSkills(courseDTO.getRequiredSkills());
        course.setCourseDescription((courseDTO.getCourseDescription()));
        course.setCourseImageUrl(courseDTO.getCourseImageUrl());
        course.setId(courseDTO.getId());
        course.setCategory(courseDTO.getCategory());
        course.setLanguage(courseDTO.getLanguage());
        course.setLevel(courseDTO.getLevel());
        course.setPrice(courseDTO.getPrice());
        course.setRating(courseDTO.getRating());
        course.setCreatedAt(courseDTO.getCreatedAt());
        course.setId(courseDTO.getId());
        course.setSale(courseDTO.getSale());
        course.setWelcomeMessage(courseDTO.getWelcomeMessage());
        course.setCongratulationMessage(courseDTO.getCongratulationMessage());
        course.setSubtitle(courseDTO.getSubtitle());
        course.setStatus(courseDTO.getStatus());
        course.setUpdatedAt(courseDTO.getUpdatedAt());
        course.setLearningObject(courseDTO.getLearningObject());
        course.setPrimarilyTaught(courseDTO.getPrimarilyTaught());
        course.setTotalEnroll(courseDTO.getTotalEnroll());
        return course;
    }
}
