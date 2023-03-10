package com.soon83.exception;

import com.soon83.CommonErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonErrorResponse> handleException(Exception e) {
        log.error("[Exception] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = ErrorCode.SYSTEM_ERROR;
        CommonErrorResponse errorResponse = CommonErrorResponse.of(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(ErrorCode.SYSTEM_ERROR.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(SystemException.class)
    protected ResponseEntity<CommonErrorResponse> handleSystemException(SystemException e) {
        log.error("[SystemException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        CommonErrorResponse errorResponse = CommonErrorResponse.of(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<CommonErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("[IllegalArgumentException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = ErrorCode.ILLEGAL_ARGUMENT_ERROR;
        CommonErrorResponse errorResponse = CommonErrorResponse.of(
                errorCode.getStatus(),
                errorCode.getCode(),
                String.format("%s %s", errorCode.getMessage(), NestedExceptionUtils.getMostSpecificCause(e).getMessage())
        );
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<CommonErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = ErrorCode.ARGUMENT_NOT_VALID_ERROR;
        CommonErrorResponse errorResponse = CommonErrorResponse.of(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage(), e.getBindingResult());
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<CommonErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = ErrorCode.REQUEST_JSON_PARSE_ERROR;
        CommonErrorResponse errorResponse = CommonErrorResponse.of(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    // TODO 인증 401
    // TODO 권한 403
    // TODO 올바르지 않은 resource mapping 405
}
