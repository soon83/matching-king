package com.soon83.exception.limit;

import com.soon83.exception.ErrorCode;
import com.soon83.exception.SystemException;

public class LimitNotFoundException extends SystemException {
    public LimitNotFoundException() {
        super(ErrorCode.LIMIT_NOT_FOUND_ERROR);
    }
}
