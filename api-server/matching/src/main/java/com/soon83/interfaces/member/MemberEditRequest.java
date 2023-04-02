package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberEditCommand;
import com.soon83.interfaces.matchingcondition.MatchingConditionEditRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MemberEditRequest(
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
        MatchingConditionEditRequest memberMatchingCondition
) {
    public MemberEditCommand toEditMemberCommand() {
        return MemberEditCommand.builder()
                .nickname(memberNickname)
                .age(memberAge)
                .gender(memberGender)
                .mbti(memberMbti)
                .build();
    }
}
