package com.soon83.interfaces.auth;

import com.soon83.domain.auth.AuthUserQuery;
import com.soon83.domain.member.Member;
import lombok.Builder;

@Builder
public record CurrentUser(
        Long memberId,
        String memberEmail,
        String memberNickname,
        Member.Role memberRole
) {
    public CurrentUser(AuthUserQuery query) {
        this(
                query.id(),
                query.email(),
                query.nickname(),
                query.role()
        );
    }
}
