package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberQuery;
import lombok.Builder;

@Builder
public record MemberResponse(
        Long memberId,
        String memberEmail,
        String memberNickname,
        int memberAge,
        Member.Gender memberGender,
        Member.Mbti memberMbti,
        Member.Type memberType,
        Member.Role memberRole
) {
    public MemberResponse(MemberQuery query) {
        this(
                query.id(),
                query.email(),
                query.nickname(),
                query.age(),
                query.gender(),
                query.mbti(),
                query.type(),
                query.role()
        );
    }
}
