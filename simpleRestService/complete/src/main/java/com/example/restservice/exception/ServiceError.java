package com.example.restservice.exception;

public class ServiceError extends Exception {

    public ServiceError(final String message) {
        super(message);
    }

    public ServiceError(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
