package com.botscrew.library.exceptions;

public class UnknownCommandException extends RuntimeException {

    public UnknownCommandException() {
    }

    public UnknownCommandException(String message) {
        super(message);
    }
}
