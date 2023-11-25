package com.team47.udemybackend.dto.assignment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAssignmentDTO {
    @JsonIgnore
    private int id;
    private String type;
    private String description;
    @JsonProperty("attached_files_url")
    private String attachedFilesUrl;
    @JsonProperty("course_id")
    private Long courseID;
    @JsonIgnore
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonIgnore
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
