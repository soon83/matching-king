package com.soon83.domain.member;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import lombok.Builder;

@Builder
public record MemberCreateMemberCommand(
        String email,
        String password,
        String nickname,
        int age,
        Member.Gender gender,
        Member.Mbti mbti,
        Member.Type type,
        Member.Role role
) {
    public MemberCreateMemberCommand {
        type = Member.Type.FREE;
        role = Member.Role.MEMBER;
    }

    public Member toEntity(
            Limit limit,
            MatchingCondition matchingCondition,
            String encodedPassword
    ) {
        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .age(age)
                .gender(gender)
                .mbti(mbti)
                .type(type)
                .role(role)
                .limit(limit)
                .matchingCondition(matchingCondition)
                .build();
    }
}
