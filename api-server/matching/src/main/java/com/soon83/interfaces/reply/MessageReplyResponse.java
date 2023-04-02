package com.soon83.interfaces.reply;

import com.soon83.domain.receivemessage.reply.MessageReplyQuery;
import lombok.Builder;

@Builder
public record MessageReplyResponse(
        Long messageReplyId,
        String content,
        Long replyMemberId
) {
    public MessageReplyResponse(MessageReplyQuery query) {
        this(
                query.id(),
                query.content(),
                query.replyMemberId()
        );
    }
}
