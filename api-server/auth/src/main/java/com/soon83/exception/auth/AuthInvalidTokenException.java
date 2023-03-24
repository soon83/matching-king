package com.soon83.exception.auth;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class AuthInvalidTokenException extends SystemException {
    public AuthInvalidTokenException() {
        super(ErrorCode.COMMON_INVALID_TOKEN_ERROR);
    }
}
