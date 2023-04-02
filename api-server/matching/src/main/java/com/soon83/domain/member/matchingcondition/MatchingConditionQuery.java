package com.soon83.domain.member.matchingcondition;

import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import lombok.Builder;

@Builder
public record MatchingConditionQuery(
        Long id,
        int minAge,
        int maxAge,
        Gender gender,
        Mbti mbti
) {
    public MatchingConditionQuery(MatchingCondition entity) {
        this(
                entity.getId(),
                entity.getMinAge(),
                entity.getMaxAge(),
                entity.getGender(),
                entity.getMbti()
        );
    }
}
