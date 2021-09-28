package com.apiProject.exception;

public class InternalTestGeneralException extends RuntimeException {
    private static final String MESSAGE = "Internal test general exception";

    public InternalTestGeneralException() {
        super("Internal test general exception");
    }

    public InternalTestGeneralException(String message) {
        super(message);
    }

    public InternalTestGeneralException(String message, Throwable cause) {
        super(message, cause);
    }
}
