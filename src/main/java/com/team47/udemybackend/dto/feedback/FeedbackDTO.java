package com.team47.udemybackend.dto.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    private int id;
    private int rating;
    @JsonProperty("feed_back")
    private String feedBack;
    private Timestamp time;
    @JsonProperty("course_id")
    private int courseID;
    @JsonProperty("user_id")
    private int userID;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
