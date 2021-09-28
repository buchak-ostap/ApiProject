package com.apiProject.exception;

import com.apiProject.exception.code.ErrorCode;

public class SystemException extends RuntimeException {

    private final ErrorCode errorCode;
    public SystemException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
