package com.botscrew.library.exceptions;

public class NoSuchBookFoundException extends RuntimeException {

    public NoSuchBookFoundException() {
    }

    public NoSuchBookFoundException(String message) {
        super(message);
    }
}
