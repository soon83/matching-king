package com.soon83.infrastructure.message.reply;

import com.soon83.domain.message.reply.MessageReply;

import java.util.Optional;

public interface MessageReplyRepositoryQuerydsl {
    Optional<MessageReply> findLatelyMessageReplyById(Long messageId);
}
