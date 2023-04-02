package com.soon83.interfaces.matchingcondition;

import com.soon83.domain.member.matchingcondition.MatchingConditionQuery;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import lombok.Builder;

@Builder
public record MatchingConditionResponse(
        Long id,
        int minAge,
        int maxAge,
        Gender gender,
        Mbti mbti
) {
    public MatchingConditionResponse(MatchingConditionQuery.Main query) {
        this(
                query.getId(),
                query.getMinAge(),
                query.getMaxAge(),
                query.getGender(),
                query.getMbti()
        );
    }
}
