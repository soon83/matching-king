package com.soon83.domain.member;

import com.soon83.domain.member.matchingcondition.MatchingCondition;

import java.util.List;

public interface MemberReader {
    List<Member> readAll(MemberQuery.SearchCondition condition);
    List<Member> readLimitMembersByMatchingCondition(Long senderId, MatchingCondition matchingCondition, int limit);
    Member readById(Long memberId);
    Member readMemberDetailById(Long memberId);
    Member readByEmail(String memberEmail);
    Member readMemberMatchingConditionAndLimitById(Long memberId);
    void checkAlreadyExistsEmail(String email);
}
