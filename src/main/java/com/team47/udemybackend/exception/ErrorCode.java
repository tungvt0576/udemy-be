package com.team47.udemybackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid Parameter"),
    VALIDATE(HttpStatus.UNPROCESSABLE_ENTITY, "Validate exception"),
    SYSTEM_OTHER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Other System error"),
    SYSTEM_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "Service unavailable");

    private final HttpStatus httpStatus;
    private final String errorMessage;

}
