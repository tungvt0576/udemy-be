package com.team47.udemybackend.exception;


import com.team47.udemybackend.dto.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorObject> handleCourseNotFoundException(CourseNotFoundException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleException(HttpServletRequest request, Exception ex) {
        return this.toResponseEntity(ErrorCode.SYSTEM_UNAVAILABLE, ex.getMessage());
    }
    @ExceptionHandler(UdemyRuntimeException.class)
    public ResponseEntity<BaseResponse> handleHttpServerErrorException(UdemyRuntimeException ex) {
        return this.toResponseEntity(ErrorCode.SYSTEM_OTHER_ERROR, ex.getMessage());
    }
    @ExceptionHandler(UdemyValidateException.class)
    public ResponseEntity<BaseResponse> handleHttpServerErrorException(UdemyValidateException ex) {
        return this.toResponseEntity(ErrorCode.VALIDATE, ex.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse> handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
        return this.toResponseEntity(ErrorCode.NOT_FOUND, ex.getMessage());
    }
    private ResponseEntity<BaseResponse> toResponseEntity(ErrorCode errorCode, String errorMessage) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(BaseResponse.builder()
                        .isError(true)
                        .message(errorMessage)
                        .build()
                );
    }
}
