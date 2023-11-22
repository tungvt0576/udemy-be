package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.dto.CourseDTO;
import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.service.CourseService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@Data
@NoArgsConstructor
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream().map(this::mapToCourseDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> listAll(String keyword) {
        if(keyword != null){
            return courseRepository.search(keyword).stream().map(this::mapToCourseDTO).collect(Collectors.toList());
        }
        return courseRepository.findAll().stream().map(this::mapToCourseDTO).collect(Collectors.toList());
    }

    @Override
    public CourseDTO findById(Long Id) {
        Optional<Course> result = courseRepository.findById(Id);
        if(result.isPresent()){
            Course course = result.get();
            return mapToCourseDTO(course);
        }else {
            throw new RuntimeException(String.format("Course Id: %d does not exist", Id));
        }
    }

    @Override
    public CourseDTO createNew(Course course) {
        Course course_ = courseRepository.save(course);
        return mapToCourseDTO(course_);
    }
    @Override
    public CourseDTO updateInfoById(Long id) throws CourseNotFoundException {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course could not be found"));
        Course course_ =  courseRepository.save(course);
        return mapToCourseDTO(course_);
    }

    @Override
    public void delete(Long id) throws CourseNotFoundException {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course could not be found"));
        courseRepository.delete(course);
    }

    private CourseDTO mapToCourseDTO(Course course){
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
        courseDTO.setUpdatedAt(course.getUpdatedAt());
        courseDTO.setLearningObject(course.getLearningObject());
        courseDTO.setPrimarilyTaught(course.getPrimarilyTaught());
        return courseDTO;
    }

    private Course mapToCourse(CourseDTO courseDTO){
        Course course = new Course();
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
        course.setUpdatedAt(courseDTO.getUpdatedAt());
        course.setLearningObject(courseDTO.getLearningObject());
        course.setPrimarilyTaught(courseDTO.getPrimarilyTaught());
        return course;
    }
}
