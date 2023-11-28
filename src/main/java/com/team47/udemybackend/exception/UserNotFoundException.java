package com.team47.udemybackend.exception;

import java.io.Serial;

public class UserNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 2;

    public UserNotFoundException(String message) {
        super(message);
    }

}
