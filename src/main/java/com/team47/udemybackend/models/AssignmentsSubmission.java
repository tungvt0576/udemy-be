package com.team47.udemybackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team47.udemybackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="assignments_submission")
public class AssignmentsSubmission {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "assignment_id_ass")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "user_id_ass")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;


    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

}
