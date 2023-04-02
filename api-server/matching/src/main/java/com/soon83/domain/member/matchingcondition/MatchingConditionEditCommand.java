package com.soon83.domain.member.matchingcondition;

import com.soon83.domain.member.Member;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import lombok.Builder;

@Builder
public record MatchingConditionEditCommand(
        int minAge,
        int maxAge,
        Gender gender,
        Mbti mbti
) {
    public void update(Member member) {
        member.getMatchingCondition().update(minAge, maxAge, gender, mbti);
    }
}
