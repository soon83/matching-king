package com.soon83.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 공통 오류
    SYSTEM_ERROR(500, "COM001", "일시적인 오류가 발생하였습니다. 잠시 후 다시 시도해주세요."),
    ARGUMENT_NOT_VALID_ERROR(400, "COM002", "올바르지 않은 파라미터입니다."),
    REQUEST_JSON_PARSE_ERROR(400, "COM003", "올바르지 않은 포맷입니다."),
    ARGUMENT_TYPE_MISMATCH_ERROR(400, "COM004", "올바르지 않은 파라미터입니다."),
    ILLEGAL_ARGUMENT_ERROR(400, "COM005", "필수 파라미터가 없습니다."),
    COMMON_INVALID_TOKEN_ERROR(401, "COM006", "유효하지 않은 토큰입니다."),
    FORBIDDEN_ERROR(403, "COM007", "접근이 불가합니다."),
    METHOD_NOT_ALLOWED_ERROR(405, "COM008", "지원하지 않는 메서드입니다."),

    // 인증 오류
    UNAUTHORIZED_ERROR(401, "ATH001", "인증오류가 발생하였습니다."),

    // 회원 오류
    MEMBER_NOT_FOUND_ERROR(404, "MBR001", "존재하지 않는 회원입니다."),
    MEMBER_ALREADY_EXISTS_ERROR(409, "MBR002", "이미 존재하는 회원입니다."),

    // 회원 제약사항 오류
    LIMIT_NOT_FOUND_ERROR(404, "LIM001", "존재하지 않는 제약사항입니다."),

    // 메시지 오류
    MESSAGE_NOT_FOUND_ERROR(404, "MSG001", "존재하지 않는 메시지입니다."),
    MESSAGE_TRANSFER_LIMIT_EXCEEDED(400, "MSG002", "전송 가능한 메시지 수를 초과하였습니다."),

    // 받은 메시지 오류
    RECEIVE_MESSAGE_NOT_FOUND_ERROR(404, "RCV001", "존재하지 않는 수신 메시지입니다."),
    NOT_MY_RECEIVE_MESSAGE_ERROR(403, "RCV002", "메시지 수정권한이 없습니다."),

    // 메시지 답장 오류
    MESSAGE_REPLY_NOT_FOUND_ERROR(404, "MRP001", "존재하지 않는 메시지입니다."),
    MESSAGE_REPLY_SERIES_ERROR(400, "MRP002", "상대방의 쪽지에만 답장이 가능합니다."),

    // 6. 인증 오류

    ;

    private final int status;
    private final String code;
    private final String message;
}
