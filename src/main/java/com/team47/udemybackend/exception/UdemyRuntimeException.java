package com.team47.udemybackend.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UdemyRuntimeException extends RuntimeException {
    @JsonProperty("error_list")
    private List<Object> errorList;

    @JsonProperty("error_map")
    private Map<String, Object> errMap;
    public UdemyRuntimeException(Throwable e) {
        super(e);
    }

    public UdemyRuntimeException(String message) {
        super(message);
    }

    public UdemyRuntimeException(String message, Map<String, Object> errMap) {
        super(message);
        this.errMap = Map.copyOf(errMap);
    }
    public UdemyRuntimeException(String message, List<?> errorList) {
        super(message);
        this.errorList = List.copyOf(errorList);
    }
}
