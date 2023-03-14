package com.soon83.domain.limit;

import com.soon83.domain.member.Member;

public interface LimitReader {
    Limit readByMemberType(Member.Type memberType);
}
