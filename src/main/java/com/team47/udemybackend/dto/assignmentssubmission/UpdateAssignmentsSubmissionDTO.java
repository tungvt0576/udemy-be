package com.team47.udemybackend.dto.assignmentssubmission;

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
public class UpdateAssignmentsSubmissionDTO {
    private int id;
    @JsonProperty("assignment_id")
    private int assignmentID;
    @JsonProperty("user_id")
    private int userID;
    @JsonIgnore
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonIgnore
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}

