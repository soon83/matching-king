package com.soon83.domain.member;

import lombok.Builder;

@Builder
public record MemberSearchConditionQuery(
        String email,
        String nickname,
        Integer age,
        Member.Gender gender,
        Member.Mbti mbti,
        Member.Type type,
        Member.Role role
) {
}
