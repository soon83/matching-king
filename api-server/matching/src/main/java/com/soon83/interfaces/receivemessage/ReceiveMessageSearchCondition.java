package com.soon83.interfaces.receivemessage;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ReceiveMessageSearchCondition(
        @NotNull(message = "필수값")
        Long targetMemberId
) {
}
