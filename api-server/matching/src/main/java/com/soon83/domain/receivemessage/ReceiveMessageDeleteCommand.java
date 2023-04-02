package com.soon83.domain.receivemessage;

import lombok.Builder;

@Builder
public record ReceiveMessageDeleteCommand(
        Long targetMemberId
) {
}
