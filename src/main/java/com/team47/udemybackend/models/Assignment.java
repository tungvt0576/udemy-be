package com.team47.udemybackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team47.udemybackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="assignments")
public class Assignment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name="attached_files_url")
    private String attachedFilesUrl;

    @ManyToOne
    @JoinColumn(name = "course_id_ass")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

}
