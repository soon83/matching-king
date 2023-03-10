package com.soon83.exception.member;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class MemberNotFoundException extends SystemException {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND_ERROR);
    }
}
