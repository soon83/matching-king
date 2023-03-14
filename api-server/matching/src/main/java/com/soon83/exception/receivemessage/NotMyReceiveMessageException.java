package com.soon83.exception.receivemessage;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class NotMyReceiveMessageException extends SystemException {
    public NotMyReceiveMessageException() {
        super(ErrorCode.NOT_MY_RECEIVE_MESSAGE_ERROR);
    }
}
