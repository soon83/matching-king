package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberDetailQuery;
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
    public MemberDetailResponse(MemberDetailQuery query) {
        this(
                query.id(),
                query.email(),
                query.nickname(),
                query.age(),
                query.gender(),
                query.mbti(),
                query.type(),
                query.role(),
                new LimitResponse(query.limit()),
                new MatchingConditionResponse(query.matchingCondition())
        );
    }
}
