package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberQuery;
import com.soon83.interfaces.limit.LimitResponse;
import com.soon83.interfaces.matchingcondition.MatchingConditionResponse;
import lombok.Builder;

@Builder
public record MemberDetailResponse(
        Long memberId,
        String memberEmail,
        String memberNickname,
        int memberAge,
        Member.Gender memberGender,
        Member.Mbti memberMbti,
        Member.Type memberType,
        Member.Role memberRole,
        LimitResponse memberLimit,
        MatchingConditionResponse memberMatchingCondition
) {
    public MemberDetailResponse(MemberQuery.Detail query) {
        this(
                query.getId(),
                query.getEmail(),
                query.getNickname(),
                query.getAge(),
                query.getGender(),
                query.getMbti(),
                query.getType(),
                query.getRole(),
                new LimitResponse(query.getLimit()),
                new MatchingConditionResponse(query.getMatchingCondition())
        );
    }
}
