package com.soon83.domain.auth;

import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class AuthUser {
    private final Long memberId;
    private final String memberEmail;
    private final Member.Role memberRole;

    @Builder
    public AuthUser(
            Long memberId,
            String memberEmail,
            Member.Role memberRole
    ) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberRole = memberRole;
    }

    public AuthUser(AuthQuery.Info info) {
        this.memberId = info.getMemberId();
        this.memberEmail = info.getMemberEmail();
        this.memberRole = info.getMemberRole();
    }
}