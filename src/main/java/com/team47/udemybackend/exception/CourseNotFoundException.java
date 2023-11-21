package com.team47.udemybackend.exception;

import java.io.Serial;

public class CourseNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = 1;
    public CourseNotFoundException(String message) {super(message);}
}
