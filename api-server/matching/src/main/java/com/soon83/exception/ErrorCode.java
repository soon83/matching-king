package com.soon83.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 0. 공통 오류
    SYSTEM_ERROR(500, "COM001", "일시적인 오류가 발생하였습니다. 잠시 후 다시 시도해주세요."),
    ARGUMENT_NOT_VALID_ERROR(400, "COM002", "올바르지 않은 파라미터입니다."),
    REQUEST_JSON_PARSE_ERROR(400, "COM003", "올바르지 않은 포맷입니다."),
    ARGUMENT_TYPE_MISMATCH_ERROR(400, "COM004", "올바르지 않은 파라미터입니다."),
    ILLEGAL_ARGUMENT_ERROR(400, "COM005", "필수 파라미터가 없습니다."),

    // 1. 회원 오류
    MEMBER_NOT_FOUND_ERROR(404, "MBR001", "존재하지 않는 회원입니다."),
    MEMBER_ALREADY_EXISTS_ERROR(409, "MBR002", "이미 존재하는 회원입니다."),

    // 2. 회원 제약사항 오류
    LIMIT_NOT_FOUND_ERROR(404, "LIM001", "존재하지 않는 제약사항 입니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
