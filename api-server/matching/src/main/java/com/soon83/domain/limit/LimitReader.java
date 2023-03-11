package com.soon83.domain.limit;

import com.soon83.domain.member.Member;

import java.util.List;

public interface LimitReader {
    List<Limit> readAll();
    Limit readById(Long limitId);
    Limit readByMemberType(Member.Type memberType);
}
