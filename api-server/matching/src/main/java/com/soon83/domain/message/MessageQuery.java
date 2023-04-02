package com.soon83.domain.message;

import lombok.Builder;

@Builder
public record MessageQuery(
        Long id,
        String content,
        Long senderId
) {
    public MessageQuery(Message entity) {
        this(
                entity.getId(),
                entity.getContent(),
                entity.getSender().getId()
        );
    }
}
