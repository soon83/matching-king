package com.soon83.exception.receivemessage;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class ReceiveMessageNotFoundException extends SystemException {
    public ReceiveMessageNotFoundException() {
        super(ErrorCode.RECEIVE_MESSAGE_NOT_FOUND_ERROR);
    }
}
