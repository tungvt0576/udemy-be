package com.team47.udemybackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRespone {
    @JsonProperty("access_token")
    private String token;
    @JsonProperty("user_id")
    private Integer userId;
}

