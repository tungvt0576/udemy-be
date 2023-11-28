package com.team47.udemybackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="lectures")
public class Lecture {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column(name="name")
        private String name;
        @Column(name = "video_url")
        private String videoUrl;
        @ManyToOne
        @JoinColumn(name = "course_id_lec")
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        private Course course;
        @ManyToOne
        @JoinColumn(name = "section_id_lec")
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        private Section section;
        @Column(name="created_at")
        private LocalDateTime createdAt;
        @Column(name="updated_at")
        private LocalDateTime updatedAt;
}
