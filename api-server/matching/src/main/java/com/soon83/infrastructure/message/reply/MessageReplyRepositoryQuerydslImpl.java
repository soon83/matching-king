package com.soon83.infrastructure.message.reply;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soon83.domain.message.reply.MessageReply;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.soon83.domain.message.reply.QMessageReply.messageReply;
import static com.soon83.infrastructure.CustomExpressionUtil.eq;

@RequiredArgsConstructor
public class MessageReplyRepositoryQuerydslImpl implements MessageReplyRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MessageReply> findLatelyMessageReplyById(Long messageId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(messageReply)
                .where(eq(messageReply.message.id, messageId))
                .orderBy(messageReply.message.id.desc())
                .fetchFirst());
    }
}
