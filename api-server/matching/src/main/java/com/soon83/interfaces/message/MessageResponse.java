package com.soon83.interfaces.message;

import com.soon83.domain.message.MessageQuery;
import lombok.Builder;

@Builder
public record MessageResponse(
        Long messageId,
        String content,
        Long senderId
) {
    public MessageResponse(MessageQuery.Main query) {
        this(
                query.getId(),
                query.getContent(),
                query.getSenderId()
        );
    }
}
