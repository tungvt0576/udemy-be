package com.team47.udemybackend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.team47.udemybackend.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class DataListResponse extends BaseResponse {
    @JsonProperty("data")
    List dataList;
    @JsonProperty("total")
    private long total;
    @JsonProperty("page_size")
    Integer pageSize;
    @JsonProperty("page_num")
    Integer pageNum;
}
