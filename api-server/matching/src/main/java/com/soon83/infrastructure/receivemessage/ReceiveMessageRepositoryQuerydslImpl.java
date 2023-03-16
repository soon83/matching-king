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
    public List<ReceiveMessage> searchReceiveMessagesOfTargetMember(Long targetMemberId) {
        return queryFactory
                .selectFrom(receiveMessage)
                .join(receiveMessage.message).fetchJoin()
                .join(receiveMessage.notification).fetchJoin()
                .join(receiveMessage.sender).fetchJoin()
                .where(
                        eq(receiveMessage.targetMember.id, targetMemberId),
                        (
                                receiveMessage.sender.id.eq(receiveMessage.targetMember.id)
                                        .and(receiveMessage.hiddenFromSender.isFalse())
                                        .or(receiveMessage.sender.id.ne(receiveMessage.targetMember.id)
                                                .and(receiveMessage.hiddenFromTargetMember.isFalse()))
                        )
                )
                .fetch();
        /**
         * where target_member_id = 1
         *   and (
         *         (sender_id = 1 and hidden_from_sender = false)
         *         or
         *         (sender_id != 1 and hidden_from_target_member = false)
         *   )
         */
    }

    @Override
    public List<ReceiveMessage> searchNotificationsOfTargetMember(Long targetMemberId) {
        return queryFactory
                .selectFrom(receiveMessage)
                .join(receiveMessage.notification).fetchJoin()
                .join(receiveMessage.sender).fetchJoin()
                .where(
                        eq(receiveMessage.targetMember.id, targetMemberId),
                        ne(receiveMessage.sender.id, targetMemberId),
                        eq(receiveMessage.notification.isDeleted, false))
                .fetch();
    }
}