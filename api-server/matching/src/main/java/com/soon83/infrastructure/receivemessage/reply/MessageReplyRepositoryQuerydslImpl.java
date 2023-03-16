package com.soon83.infrastructure.receivemessage.reply;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soon83.domain.receivemessage.reply.MessageReply;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.soon83.domain.receivemessage.reply.QMessageReply.messageReply;
import static com.soon83.infrastructure.CustomExpressionUtil.eq;

@RequiredArgsConstructor
public class MessageReplyRepositoryQuerydslImpl implements MessageReplyRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MessageReply> findLatelyMessageReplyByMessageId(Long messageId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(messageReply)
                .where(eq(messageReply.receiveMessage.message.id, messageId))
                .orderBy(messageReply.receiveMessage.message.id.desc())
                .fetchFirst());
    }
}
