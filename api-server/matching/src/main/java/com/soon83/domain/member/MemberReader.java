package com.soon83.domain.member;

import com.soon83.domain.member.model.MemberQuery;

import java.util.List;

public interface MemberReader {
    List<Member> readAll(MemberQuery.SearchCondition condition);
    Member readById(Long memberId);
}
