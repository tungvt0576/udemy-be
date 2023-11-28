package com.team47.udemybackend.exception;

import java.io.Serial;

public class SectionNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = 1;
    public SectionNotFoundException(String message) {
        super(message);
    }
}
