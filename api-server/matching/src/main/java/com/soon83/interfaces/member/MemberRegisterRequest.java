package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberCreateCommand;
import com.soon83.interfaces.matchingcondition.MatchingConditionRegisterRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record MemberRegisterRequest(
        @NotBlank(message = "필수값")
        @Email(message = "올바른 이메일 형식을 입력해주세요")
        String memberEmail,
        @NotBlank(message = "필수값")
        String memberPassword,
        @NotBlank(message = "필수값")
        String memberNickname,
        @NotNull(message = "필수값")
        @Min(value = 5, message = "최소 5 이상")
        @Max(value = 100, message = "최대 100 이하")
        int memberAge,
        @NotNull(message = "필수값")
        Member.Gender memberGender,
        @NotNull(message = "필수값")
        Member.Mbti memberMbti,
        @Valid
        @NotNull(message = "필수값")
        MatchingConditionRegisterRequest memberMatchingCondition
) {
    public MemberCreateCommand toCreateMemberCommand() {
        return MemberCreateCommand.builder()
                .email(memberEmail)
                .password(memberPassword)
                .nickname(memberNickname)
                .age(memberAge)
                .gender(memberGender)
                .mbti(memberMbti)
                .build();
    }
}
