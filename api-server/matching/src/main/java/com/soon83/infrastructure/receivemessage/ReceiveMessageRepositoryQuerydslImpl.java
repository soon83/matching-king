package com.soon83.infrastructure.receivemessage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soon83.domain.receivemessage.ReceiveMessage;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.soon83.domain.receivemessage.QReceiveMessage.receiveMessage;

@RequiredArgsConstructor
public class ReceiveMessageRepositoryQuerydslImpl implements ReceiveMessageRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReceiveMessage> searchReceiveMessagesOfMember(Long targetMemberId) {
        return queryFactory
                .selectFrom(receiveMessage)
                .join();
    }
}