package com.team47.udemybackend.dto.lecture_status;

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
public class CreateLectureStatusDTO {
    @JsonIgnore
    private int id;
    private boolean status;
    @JsonProperty("lecture_id")
    private int lectureID;
    @JsonProperty("user_id")
    private int userID;
    @JsonIgnore
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonIgnore
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
