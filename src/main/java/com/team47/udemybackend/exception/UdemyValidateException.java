package com.team47.udemybackend.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UdemyValidateException extends  UdemyRuntimeException{
    public UdemyValidateException(String message) {
        super(message);
    }
    public UdemyValidateException(String message, Map<String, Object> errorMap) {
        super(message, errorMap);
        this.setErrMap(errorMap);
    }
}
