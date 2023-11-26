package com.team47.udemybackend.dto.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateFeedbackDTO {
        private int id;
        private int rating;
        @JsonProperty("feed_back")
        private String feedBack;
        private Timestamp time;
        @JsonProperty("course_id")
        private int courseID;
        @JsonProperty("user_id")
        private int userID;
        @JsonIgnore
        @JsonProperty("created_at")
        private LocalDateTime createdAt;
        @JsonIgnore
        @JsonProperty("updated_at")
        private LocalDateTime updatedAt;
}
