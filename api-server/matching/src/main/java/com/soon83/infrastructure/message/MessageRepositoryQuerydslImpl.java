package com.soon83.infrastructure.message;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soon83.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

import static com.soon83.domain.message.QMessage.message;

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

    public static BooleanExpression eq(NumberPath<Long> domainValue, Long value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static BooleanExpression goe(DateTimePath<LocalDateTime> domainValue, LocalDateTime value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.goe(value);
    }

}