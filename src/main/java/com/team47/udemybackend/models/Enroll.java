package com.team47.udemybackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team47.udemybackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrolls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enroll {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id_enr")
    private User user;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id_enr")
    private Course course;
}
