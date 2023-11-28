package com.team47.udemybackend.models;

import com.team47.udemybackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="feedbacks")
public class Feedback {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="rating")
    private int rating;
    @Column(name = "feed_back")
    private String feedBack;
    @Column(name ="time")
    private Timestamp time;

    @ManyToOne
    @JoinColumn(name = "course_id_fee")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id_fee")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;


    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

}
