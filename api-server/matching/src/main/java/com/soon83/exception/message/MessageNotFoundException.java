package com.soon83.exception.message;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class MessageNotFoundException extends SystemException {
    public MessageNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND_ERROR);
    }
}
