package com.soon83.interfaces.message;

import lombok.Builder;

@Builder
public record MessageRegisterResponse(
        Long messageId
) {
}
