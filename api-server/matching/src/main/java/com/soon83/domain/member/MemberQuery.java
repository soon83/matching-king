package com.soon83.domain.member;

import lombok.Builder;

@Builder
public record MemberQuery(
        Long id,
        String email,
        String password,
        String nickname,
        int age,
        Member.Gender gender,
        Member.Mbti mbti,
        Member.Type type,
        Member.Role role
) {
    public MemberQuery(Member entity) {
        this(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getNickname(),
                entity.getAge(),
                entity.getGender(),
                entity.getMbti(),
                entity.getType(),
                entity.getRole()
        );
    }
}
