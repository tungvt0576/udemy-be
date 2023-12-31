package com.team47.udemybackend.service.implement;

import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.exception.SectionNotFoundException;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.models.Section;
import com.team47.udemybackend.repository.CourseRepository;
import com.team47.udemybackend.repository.SectionRepository;
import com.team47.udemybackend.service.SectionService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    Logger log = LoggerFactory.getLogger(SectionServiceImpl.class);

    public SectionServiceImpl(SectionRepository sectionRepository, CourseRepository courseRepository) {
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public Section findSectionByIdAndCourseId(Integer sectionId, Integer courseId) throws SectionNotFoundException {
        return sectionRepository.findSectionsByIdAndCourseId(sectionId, courseId);
    }

    @Override
    public List<Section> findAllSectionByCourseId(Integer courseId) throws SectionNotFoundException {
        return sectionRepository.findSectionsByCourseId(courseId);
    }

    @Override
    public Section updateSectionByName(String newName, Integer sectionId, Integer courseId) {
        Section section = sectionRepository.findSectionsByIdAndCourseId(sectionId, courseId);
        section.setName(newName);
        section.setUpdatedAt(LocalDateTime.now());
        sectionRepository.save(section);
        return section;
    }

    @Override
    public Section createSection(String newName, Integer courseID) throws CourseNotFoundException {
        Optional<Course> courseResult = courseRepository.findById(courseID);
        Course course;
        if(courseResult.isPresent()){
            course = courseResult.get();
        }else {
            throw new CourseNotFoundException("Course not found!");
        }
        Section section =  Section.builder()
                .name(newName)
                .course(course)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        log.info(section.toString());
        sectionRepository.save(section);
        return section;

    }

    @Override
    public void deleteSection(Integer sectionId, Integer courseId) throws SectionNotFoundException {
        Section section = sectionRepository.findSectionsByIdAndCourseId(sectionId, courseId);
        if(section != null){
            sectionRepository.deleteSectionByIdAndCourseId(sectionId, courseId);
        }else{
            throw new SectionNotFoundException(String.format("Section %d in course %d not found!", sectionId, courseId));
        }

    }

    @Override
    public void deleteAllSectionByCourseId(Integer courseId) throws CourseNotFoundException, SectionNotFoundException {
        if(sectionRepository.findSectionsByCourseId(courseId) != null){
            sectionRepository.deleteSectionsByCourseId(courseId);
        }else{
            throw new SectionNotFoundException(String.format("No any section in course %d", courseId));
        }

    }
}
