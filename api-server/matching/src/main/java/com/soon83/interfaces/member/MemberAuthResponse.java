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
    public MemberAuthResponse(MemberQuery.Main query) {
        this(
                query.getId(),
                query.getEmail(),
                query.getPassword(),
                query.getNickname(),
                query.getRole()
        );
    }
}
