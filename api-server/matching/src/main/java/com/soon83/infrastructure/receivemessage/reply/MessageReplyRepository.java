package com.soon83.infrastructure.receivemessage.reply;

import com.soon83.domain.receivemessage.reply.MessageReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageReplyRepository extends JpaRepository<MessageReply, Long>, MessageReplyRepositoryQuerydsl {
}
