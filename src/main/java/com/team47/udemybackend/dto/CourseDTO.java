package com.team47.udemybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private int id;
    private String learningObject;
    private String requiredSkills;
    private String courseFor;
    private String title;
    private Integer totalEnroll;
    private String subtitle;
    private String courseDescription;
    private String language;
    private String level;
    private String category;
    private String primarilyTaught;
    private String courseImageUrl;
    private String promotionalVideoUrl;
    private int price;
    private String welcomeMessage;
    private String congratulationMessage;
    private String status;
    private float rating;
    private int sale;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
