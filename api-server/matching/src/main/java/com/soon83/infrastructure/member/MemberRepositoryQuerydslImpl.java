package com.soon83.infrastructure.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soon83.domain.member.Member;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.soon83.domain.member.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryQuerydslImpl implements MemberRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Member> readMemberDetailById(Long memberId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .innerJoin(member.limit).fetchJoin()
                .innerJoin(member.memberCondition).fetchJoin()
                .innerJoin(member.memberMatchingCondition).fetchJoin()
                .where(member.id.eq(memberId))
                .fetchOne());
    }

    @Override
    public Optional<Member> readMemberLimitById(Long memberId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .innerJoin(member.limit).fetchJoin()
                .where(member.id.eq(memberId))
                .fetchOne());
    }
}