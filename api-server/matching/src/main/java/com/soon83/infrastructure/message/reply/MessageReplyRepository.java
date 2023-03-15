package com.soon83.infrastructure.message.reply;

import com.soon83.domain.message.reply.MessageReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageReplyRepository extends JpaRepository<MessageReply, Long>, MessageReplyRepositoryQuerydsl {
}
