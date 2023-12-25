package com.team47.udemybackend.service;

import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.exception.SectionNotFoundException;
import com.team47.udemybackend.models.Section;

import java.util.List;

public interface SectionService {
    public Section findSectionByIdAndCourseId(Integer sectionId, Integer courseId) throws SectionNotFoundException;
    public List<Section> findAllSectionByCourseId(Integer courseId) throws SectionNotFoundException;
    public Section updateSectionByName(String name, Integer sectionId, Integer courseID) throws SectionNotFoundException;
    public Section createSection(String newName, Integer courseID) throws CourseNotFoundException;
    public void deleteSection(Integer sectionId, Integer courseId) throws SectionNotFoundException;
    public void deleteAllSectionByCourseId(Integer courseId) throws CourseNotFoundException, SectionNotFoundException;

}
