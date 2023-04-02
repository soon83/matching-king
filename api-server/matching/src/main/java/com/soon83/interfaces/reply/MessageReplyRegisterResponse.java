package com.soon83.interfaces.reply;

import lombok.Builder;

@Builder
public record MessageReplyRegisterResponse(
        Long messageReplyId
) {
}
