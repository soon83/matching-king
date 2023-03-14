package com.soon83.infrastructure.receivemessage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soon83.domain.receivemessage.ReceiveMessage;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.soon83.domain.receivemessage.QReceiveMessage.receiveMessage;
import static com.soon83.infrastructure.CustomExpressionUtil.*;

@RequiredArgsConstructor
public class ReceiveMessageRepositoryQuerydslImpl implements ReceiveMessageRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReceiveMessage> searchReceiveMessagesNotificationsOfTargetMember(Long targetMemberId) {
        return queryFactory
                .selectFrom(receiveMessage)
                .join(receiveMessage.notification).fetchJoin()
                .join(receiveMessage.sender).fetchJoin()
                .where(eq(receiveMessage.targetMember.id, targetMemberId))
                .fetch();
    }

    @Override
    public List<ReceiveMessage> searchReceiveMessagesOfTargetMember(Long targetMemberId) {
        return queryFactory
                .selectFrom(receiveMessage)
                .join(receiveMessage.message).fetchJoin()
                .join(receiveMessage.notification).fetchJoin()
                .join(receiveMessage.sender).fetchJoin()
                .where(eq(receiveMessage.targetMember.id, targetMemberId))
                .fetch();
    }
}