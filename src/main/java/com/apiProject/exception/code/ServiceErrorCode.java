package com.apiProject.exception.code;

import org.springframework.http.HttpStatus;

public enum ServiceErrorCode implements ErrorCode {

    // @formatter:off
    GENERAL(200, HttpStatus.BAD_REQUEST.value()),
    INVALID_ARGUMENT(201, HttpStatus.BAD_REQUEST.value()),
    NOT_FOUND(202, HttpStatus.NOT_FOUND.value()),
    DOWNSTREAM_ERROR(203, HttpStatus.INTERNAL_SERVER_ERROR.value()),
    BAD_REQUEST(400, HttpStatus.BAD_REQUEST.value()),
    FORBIDDEN(403, HttpStatus.FORBIDDEN.value()),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR.value())
    ;
    // @formatter:on

    private final int errorCode;
    private final int httpStatusCode;

    ServiceErrorCode(final int errorCode, final int httpStatusCode) {
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

}
