package com.team47.udemybackend.controllers;

import com.team47.udemybackend.exception.CourseNotFoundException;
import com.team47.udemybackend.exception.SectionNotFoundException;
import com.team47.udemybackend.models.Section;
import com.team47.udemybackend.service.SectionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Data
@CrossOrigin
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }
    @GetMapping("/section/{sectionId}/{courseId}")
    public ResponseEntity<Section> getSection(@PathVariable Integer sectionId, @PathVariable Integer courseId) throws SectionNotFoundException {
        return new ResponseEntity<>(sectionService.findSectionByIdAndCourseId(sectionId, courseId), HttpStatus.OK);
    }
    @GetMapping("/section/course-id/{courseID}")
    public ResponseEntity<List<Section>> getSectionsByCourseId(@PathVariable Integer courseID) throws SectionNotFoundException {
        return new ResponseEntity<>(sectionService.findAllSectionByCourseId(courseID), HttpStatus.OK);
    }
    @DeleteMapping("/section/{courseId}")
    public ResponseEntity<String> deleteAllByCourseID(@PathVariable Integer courseId) throws CourseNotFoundException {
        sectionService.deleteAllSectionByCourseId(courseId);
        return new ResponseEntity<>(String.format("Delete all section of course %d", courseId), HttpStatus.OK);
    }
    @DeleteMapping("/section/{sectionId}/{courseId}")
    public ResponseEntity<String> delete(@PathVariable Integer sectionId, @PathVariable Integer courseId) throws SectionNotFoundException {
        sectionService.findSectionByIdAndCourseId(sectionId, courseId);
        return new ResponseEntity<>(String.format("Deleted section %d of course %d", sectionId, courseId),HttpStatus.OK);
    }
    @PostMapping("/section/{courseId}")
    public ResponseEntity<Section> createNew(@RequestBody String newName,@PathVariable Integer courseId) throws CourseNotFoundException {
        return new ResponseEntity<>(sectionService.createSection(newName, courseId), HttpStatus.OK);
    }
    @PutMapping("/section/{sectionId}/{courseID}")
    public ResponseEntity<Section> updateSection(@RequestBody String newName, @PathVariable Integer sectionId, @PathVariable Integer courseID) throws SectionNotFoundException {
        return new ResponseEntity<>(sectionService.updateSectionByName(newName, sectionId, courseID), HttpStatus.OK);
    }
}
