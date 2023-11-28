package com.team47.udemybackend.dto;

import com.team47.udemybackend.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String name;
    private String website;
    private String avatar;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Float money;
    private Role role;

}
