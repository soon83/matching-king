package com.soon83.domain.member;

import com.soon83.domain.limit.LimitQuery;
import com.soon83.domain.member.matchingcondition.MatchingConditionQuery;
import lombok.Builder;

@Builder
public record MemberDetailQuery(
        Long id,
        String email,
        String nickname,
        int age,
        Member.Gender gender,
        Member.Mbti mbti,
        Member.Type type,
        Member.Role role,
        LimitQuery.Main limit,
        MatchingConditionQuery.Main matchingCondition
) {
    public MemberDetailQuery(Member entity) {
        this(
                entity.getId(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getAge(),
                entity.getGender(),
                entity.getMbti(),
                entity.getType(),
                entity.getRole(),
                new LimitQuery.Main(entity.getLimit()),
                new MatchingConditionQuery.Main(entity.getMatchingCondition())
        );
    }
}
