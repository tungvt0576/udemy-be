package com.team47.udemybackend.dto.lecture;

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
public class UpdateLectureDTO {
    private int id;
    private String name;
    @JsonProperty("video_url")
    private String videoUrl;
    @JsonProperty("course_id")
    private int courseID;
    @JsonProperty("section_id")
    private int sectionID;
    @JsonIgnore
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonIgnore
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
