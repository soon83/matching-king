package com.soon83.domain.auth;

import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class AuthUser {
    private final Long memberId;
    private final String memberEmail;
    private final String memberNickname;
    private final Member.Role memberRole;

    @Builder
    public AuthUser(
            Long memberId,
            String memberEmail,
            String memberNickname,
            Member.Role memberRole
    ) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberNickname = memberNickname;
        this.memberRole = memberRole;
    }

    public AuthUser(AuthQuery.Info info) {
        this.memberId = info.getId();
        this.memberEmail = info.getEmail();
        this.memberNickname = info.getNickname();
        this.memberRole = info.getRole();
    }
}