package com.soon83.interfaces.matchingcondition;

import com.soon83.domain.member.matchingcondition.MatchingConditionCreateCommand;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MatchingConditionRegisterRequest(
        @NotNull(message = "필수값")
        @Min(value = 5, message = "최소 5 이상")
        @Max(value = 100, message = "최대 100 이하")
        int minAge,
        @NotNull(message = "필수값")
        @Min(value = 5, message = "최소 5 이상")
        @Max(value = 100, message = "최대 100 이하")
        int maxAge,
        @NotNull(message = "필수값")
        Gender gender,
        @NotNull(message = "필수값")
        Mbti mbti
) {
    public MatchingConditionCreateCommand toCreateMatchingConditionCommand() {
        return MatchingConditionCreateCommand.builder()
                .minAge(minAge)
                .maxAge(maxAge)
                .gender(gender)
                .mbti(mbti)
                .build();
    }
}
