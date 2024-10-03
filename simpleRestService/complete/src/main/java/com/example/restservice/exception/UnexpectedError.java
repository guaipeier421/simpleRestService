package com.example.restservice.exception;

public class UnexpectedError extends ServiceError {

    public UnexpectedError(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public UnexpectedError(final String message) {
        super(message);
    }
}
