package com.soon83.infrastructure.member;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soon83.domain.member.Member;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static com.soon83.domain.member.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryQuerydslImpl implements MemberRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> readLimitMembersByMatchingCondition(MatchingCondition matchingCondition, int limit) {
        return queryFactory
                .selectFrom(member)
                .where(
                        eq(member.isActivated, true),
                        between(member.age, matchingCondition.getMinAge(), matchingCondition.getMaxAge()),
                        in(member.gender, matchingCondition.getGender().checkedList()),
                        in(member.mbti, matchingCondition.getMbti().checkedList())
                )
                .orderBy(randomOrder())
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
