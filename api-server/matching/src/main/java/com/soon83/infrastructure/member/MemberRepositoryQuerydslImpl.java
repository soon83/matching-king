package com.soon83.infrastructure.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soon83.domain.member.Member;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.soon83.domain.member.QMember.member;
import static com.soon83.infrastructure.CustomExpressionUtil.*;

@RequiredArgsConstructor
public class MemberRepositoryQuerydslImpl implements MemberRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> readLimitMembersByMatchingCondition(Long senderId, MatchingCondition matchingCondition, int limit) {
        return queryFactory
                .selectFrom(member)
                .where(
                        ne(member.id, senderId),
                        between(member.age, matchingCondition.getMinAge(), matchingCondition.getMaxAge()),
                        in(member.gender, matchingCondition.getGender().checkedList()),
                        in(member.mbti, matchingCondition.getMbti().checkedList()),
                        eq(member.isActivated, true))
                .orderBy(rand())
                .limit(limit)
                .fetch();
    }

    @Override
    public Optional<Member> readMemberDetailById(Long memberId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .innerJoin(member.limit).fetchJoin()
                .innerJoin(member.matchingCondition).fetchJoin()
                .where(eq(member.id, memberId))
                .fetchOne());
    }

    @Override
    public Optional<Member> readMemberMatchingConditionAndLimitById(Long memberId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .innerJoin(member.limit).fetchJoin()
                .innerJoin(member.matchingCondition).fetchJoin()
                .where(eq(member.id, memberId))
                .fetchOne());
    }
}
