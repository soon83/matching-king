package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberSearchConditionQuery;
import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record MemberSearchCondition(
        @Email(message = "올바른 이메일 형식을 입력해주세요")
        String memberEmail,
        String memberNickname,
        Integer memberAge,
        Member.Gender memberGender,
        Member.Mbti memberMbti,
        Member.Type memberType,
        Member.Role memberRole
) {
    public MemberSearchConditionQuery toSearchMemberCondition() {
        return MemberSearchConditionQuery.builder()
                .email(memberEmail)
                .nickname(memberNickname)
                .age(memberAge)
                .gender(memberGender)
                .mbti(memberMbti)
                .type(memberType)
                .role(memberRole)
                .build();
    }
}
