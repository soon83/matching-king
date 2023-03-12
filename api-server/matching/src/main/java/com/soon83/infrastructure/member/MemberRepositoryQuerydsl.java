package com.soon83.infrastructure.member;

import com.soon83.domain.member.Member;

import java.util.Optional;

public interface MemberRepositoryQuerydsl {
    Optional<Member> readMemberDetailById(Long memberId);
    Optional<Member> readMemberLimitById(Long memberId);
}