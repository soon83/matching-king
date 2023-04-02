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
    public MemberResponse(MemberQuery.Main query) {
        this(
                query.getId(),
                query.getEmail(),
                query.getNickname(),
                query.getAge(),
                query.getGender(),
                query.getMbti(),
                query.getType(),
                query.getRole()
        );
    }
}
