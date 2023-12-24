package com.team47.udemybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChangeMoneyRequest {
    private Float changeAmount;
    private Integer typeChange; //1 is add, 0 is less
}
