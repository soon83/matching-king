package com.soon83.domain.receivemessage.reply;

import lombok.Builder;

@Builder
public record MessageReplyQuery(
        Long id,
        String content,
        Long replyMemberId
) {
    public MessageReplyQuery(MessageReply entity) {
        this(
                entity.getId(),
                entity.getContent(),
                entity.getReplyMember().getId()
        );
    }
}
