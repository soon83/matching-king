package com.soon83.infrastructure.message;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soon83.DateTimeUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.soon83.domain.message.QMessage.message;
import static com.soon83.infrastructure.CustomExpressionUtil.eq;
import static com.soon83.infrastructure.CustomExpressionUtil.goe;

@RequiredArgsConstructor
public class MessageRepositoryQuerydslImpl implements MessageRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public int findLimitMessageByMemberId(Long memberId, int sendMessageCount) {
        return queryFactory
                .selectFrom(message)
                .where(
                        eq(message.sender.id, memberId),
                        goe(message.createdAt, DateTimeUtil.ofStartTime(LocalDateTime.now()))
                )
                .limit(sendMessageCount)
                .fetch()
                .size();
    }
}
