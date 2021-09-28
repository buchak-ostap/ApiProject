package com.apiProject.exception.code;

import java.io.Serializable;

public interface ErrorCode extends Serializable {

    int getHttpStatusCode();
    int getErrorCode();
}
