package com.team47.udemybackend.dto.assignment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.team47.udemybackend.models.Course;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTO {
    private int id;
    private String type;
    private String description;
    @JsonProperty("attached_files_url")
    private String attachedFilesUrl;
    @JsonProperty("course_id")
    private Long courseID;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
