package com.team47.udemybackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscussionDTO {
    @JsonIgnore
    private Integer id;
    private String comment;
    @JsonIgnore
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
