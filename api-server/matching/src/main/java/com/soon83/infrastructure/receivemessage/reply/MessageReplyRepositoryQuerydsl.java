package com.soon83.infrastructure.receivemessage.reply;

import com.soon83.domain.receivemessage.reply.MessageReply;

import java.util.Optional;

public interface MessageReplyRepositoryQuerydsl {
    Optional<MessageReply> findLatelyMessageReplyByMessageId(Long messageId);
}
