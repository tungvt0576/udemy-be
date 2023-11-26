package com.team47.udemybackend.dto.lecture_status;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team47.udemybackend.models.Lecture;
import com.team47.udemybackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureStatusDTO {
    private int id;
    private boolean status;
    @JsonProperty("lecture_id")
    private int lectureID;
    @JsonProperty("user_id")
    private int userID;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
