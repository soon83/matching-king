package com.soon83.domain.auth;

import com.soon83.domain.member.Member;
import lombok.Builder;

@Builder
public record AuthUserQuery(
        Long id,
        String email,
        String password,
        String nickname,
        Member.Role role
) {
    public AuthUserQuery(Member entity) {
        this(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getNickname(),
                entity.getRole()
        );
    }
}
