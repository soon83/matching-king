package com.soon83.exception.message;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class MessageReplySeriesException extends SystemException {
    public MessageReplySeriesException() {
        super(ErrorCode.MESSAGE_REPLY_SERIES_ERROR);
    }
}
