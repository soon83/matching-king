package com.soon83.exception.auth;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class AuthForbiddenException extends SystemException {
    public AuthForbiddenException() {
        super(ErrorCode.FORBIDDEN_ERROR);
    }
}
