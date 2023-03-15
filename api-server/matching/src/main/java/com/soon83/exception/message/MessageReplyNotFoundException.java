package com.soon83.exception.message;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class MessageReplyNotFoundException extends SystemException {
    public MessageReplyNotFoundException() {
        super(ErrorCode.MESSAGE_REPLY_NOT_FOUND_ERROR);
    }
}
