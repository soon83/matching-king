package com.soon83.exception.auth;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class AuthMethodNotAllowedException extends SystemException {
    public AuthMethodNotAllowedException() {
        super(ErrorCode.METHOD_NOT_ALLOWED_ERROR);
    }
}
