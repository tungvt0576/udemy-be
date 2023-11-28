package com.team47.udemybackend.exception;

import java.io.Serial;

public class EnrollNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 2;

    public EnrollNotFoundException(String message) {
        super(message);
    }
}
