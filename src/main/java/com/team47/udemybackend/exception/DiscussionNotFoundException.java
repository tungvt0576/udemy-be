package com.team47.udemybackend.exception;

import org.springframework.stereotype.Service;

import java.io.Serial;

public class DiscussionNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = 1;
    public DiscussionNotFoundException(String message) {
        super(message);
    }
}
