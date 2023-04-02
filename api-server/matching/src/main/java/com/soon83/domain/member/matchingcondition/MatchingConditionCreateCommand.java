package com.soon83.domain.member.matchingcondition;

import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import lombok.Builder;

@Builder
public record MatchingConditionCreateCommand(
        int minAge,
        int maxAge,
        Gender gender,
        Mbti mbti
) {
    public MatchingCondition toEntity() {
        return MatchingCondition.builder()
                .minAge(minAge)
                .maxAge(maxAge)
                .gender(gender)
                .mbti(mbti)
                .build();
    }
}
