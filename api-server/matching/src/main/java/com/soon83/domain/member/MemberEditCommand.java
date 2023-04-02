package com.soon83.domain.member;

import lombok.Builder;

@Builder
public record MemberEditCommand(
        String nickname,
        int age,
        Member.Gender gender,
        Member.Mbti mbti
) {
    public void update(Member member) {
        member.update(nickname, age, gender, mbti);
    }
}
