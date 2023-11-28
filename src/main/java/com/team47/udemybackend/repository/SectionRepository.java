package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.Section;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface SectionRepository extends JpaRepository<Section, Integer> {

    public Section findSectionsByIdAndCourseId(Integer sectionId, Integer courseId);
    public List<Section> findSectionsByCourseId(Integer courseId);
    public void deleteSectionByIdAndCourseId(Integer sectionId, Integer courseId);
    public void deleteSectionsByCourseId(Integer courseId);

}
