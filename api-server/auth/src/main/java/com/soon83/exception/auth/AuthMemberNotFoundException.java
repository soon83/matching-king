package com.soon83.exception.auth;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class AuthMemberNotFoundException extends SystemException {
    public AuthMemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND_ERROR);
    }
}
