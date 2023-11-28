package com.team47.udemybackend.models;

import com.team47.udemybackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "learning_object")
    private String learningObject;
    @Column(name = "required_skills")
    private String requiredSkills;
    @Column(name = "course_for")
    private String courseFor;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @Column(name = "course_description")
    private String courseDescription;
    @Column(name = "language")
    private String language;
    @Column(name = "level")
    private String level;
    @Column(name = "category")
    private String category;
    @Column(name = "primarily_taught")
    private String primarilyTaught;
    @Column(name = "course_image_url")
    private String courseImageUrl;
    @Column(name = "promotional_video_url")
    private String promotionalVideoUrl;
    @Column(name = "price")
    private int price;
    @Column(name = "welcome_message")
    private String welcomeMessage;
    @Column(name = "congratulation_message")
    private String congratulationMessage;
    @Column(name = "status")
    private String status;
    @Column(name = "rating")
    private float rating;
    @Column(name = "sale")
    private int sale;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "total_enroll")
    private Integer totalEnroll;
    @ManyToOne
    @JoinColumn(name = "user_id_cou")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToMany(mappedBy = "enrolledCourses")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> enrolledUsers = new HashSet<>();

}