package com.team47.udemybackend.models;

import com.team47.udemybackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="lecture_statuses")
public class LectureStatus {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="status")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "lecture_id_lec")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Lecture lecture;
    @ManyToOne
    @JoinColumn(name = "user_id_lec")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
