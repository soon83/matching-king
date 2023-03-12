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
                        in(member.gender, matchingCondition.getGenders()),
                        in(member.mbti, matchingCondition.getMbtiList())
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

    private static OrderSpecifier<Double> randomOrder() {
        return Expressions.numberTemplate(Double.class, "function('rand')").asc();
    }

    public static BooleanExpression eq(BooleanPath domainValue, Boolean value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static <T extends Number & Comparable<?>> BooleanExpression eq(NumberPath<T> domainValue, T value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    private static <T extends Enum<T>> BooleanExpression in(EnumPath<T> domainValue, List<T> valueList) {
        if (ObjectUtils.isEmpty(valueList)) return null;
        return domainValue.in(valueList);
    }

    private static <T extends Number & Comparable<?>> BooleanExpression between(NumberPath<T> domainValue, T start, T end) {
        if (ObjectUtils.isEmpty(start) || ObjectUtils.isEmpty(end)) return null;
        return domainValue.goe(start).and(domainValue.loe(end));
    }
}