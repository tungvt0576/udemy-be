package com.team47.udemybackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team47.udemybackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "discussions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discussion {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment")
    private String comment;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "lecture_id_dis")
    private Lecture lecture;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "user_id_dis")
    private User user;

}
