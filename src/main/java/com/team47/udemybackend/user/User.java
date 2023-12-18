package com.team47.udemybackend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team47.udemybackend.models.Course;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @ManyToMany
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(
            name = "enrolls",
            joinColumns = @JoinColumn(name = "user_id_enr"),
            inverseJoinColumns = @JoinColumn(name = "course_id_enr")

    )
    public Set<Course> enrolledCourses = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "website")
    private String website;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "description")
    private String description;
    @Column(name = "money")
    private Float money;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private Collection<Course> courses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
