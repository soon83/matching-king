package com.soon83.infrastructure.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.matchingcondition.MatchingCondition;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryQuerydsl {
    List<Member> readLimitMembersByMatchingCondition(MatchingCondition matchingCondition, int limit);
    Optional<Member> readMemberDetailById(Long memberId);
    Optional<Member> readMemberMatchingConditionAndLimitById(Long memberId);
}
