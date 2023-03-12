package com.soon83.exception.message;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class MessageTransferLimitExceededException extends SystemException {
    public MessageTransferLimitExceededException() {
        super(ErrorCode.MESSAGE_TRANSFER_LIMIT_EXCEEDED);
    }
}
