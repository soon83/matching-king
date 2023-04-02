package com.soon83.domain.message;

import lombok.Builder;

@Builder
public record MessageSearchConditionQuery(
        String content,
        Long senderId
) {
}
