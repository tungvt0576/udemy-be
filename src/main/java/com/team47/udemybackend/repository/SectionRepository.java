package com.team47.udemybackend.repository;

import com.team47.udemybackend.models.LectureStatus;
import com.team47.udemybackend.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Integer> {
}
