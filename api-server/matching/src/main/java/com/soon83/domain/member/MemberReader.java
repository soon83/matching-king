package com.soon83.domain.member;

import java.util.List;

public interface MemberReader {
    List<Member> readAll(MemberQuery.SearchCondition condition);
    Member readById(Long memberId);
    Member readMemberDetailById(Long memberId);
    Member readMemberLimitById(Long memberId);
    void checkAlreadyExistsEmail(String email);
}
