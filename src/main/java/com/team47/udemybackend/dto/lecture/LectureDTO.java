package com.team47.udemybackend.dto.lecture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.team47.udemybackend.models.Course;
import com.team47.udemybackend.models.Section;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureDTO {
    private int id;
    private String name;
    @JsonProperty("video_url")
    private String videoUrl;
    @JsonProperty("course_id")
    private int courseID;
    @JsonProperty("section_id")
    private int sectionID;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
