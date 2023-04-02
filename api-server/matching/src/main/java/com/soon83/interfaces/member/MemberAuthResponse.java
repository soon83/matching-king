package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberQuery;
import lombok.Builder;

@Builder
public record MemberAuthResponse(
        Long memberId,
        String memberEmail,
        String memberPassword,
        String memberNickname,
        Member.Role memberRole
) {
    public MemberAuthResponse(MemberQuery query) {
        this(
                query.id(),
                query.email(),
                query.password(),
                query.nickname(),
                query.role()
        );
    }
}
