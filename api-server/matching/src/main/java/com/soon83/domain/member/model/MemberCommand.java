package com.soon83.domain.member.model;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.matchingcondition.MemberMatchingCondition;
import com.soon83.domain.member.Member;
import com.soon83.domain.member.condition.MemberCondition;
import lombok.Builder;
import lombok.Data;

public class MemberCommand {

    @Data
    public static class CreateMember {
        private String email;
        private String nickname;
        private Member.Gender gender;
        private Member.Mbti mbti;
        private Member.Type type = Member.Type.FREE;
        private Member.Role role = Member.Role.MANAGER;

        @Builder
        public CreateMember(
                String email,
                String nickname,
                Member.Gender gender,
                Member.Mbti mbti
        ) {
            this.email = email;
            this.nickname = nickname;
            this.gender = gender;
            this.mbti = mbti;
        }

        public Member toEntity(Limit limit, MemberCondition memberCondition, MemberMatchingCondition memberMatchingCondition) {
            return Member.builder()
                    .email(email)
                    .nickname(nickname)
                    .gender(gender)
                    .mbti(mbti)
                    .type(type)
                    .role(role)
                    .limit(limit)
                    .memberCondition(memberCondition)
                    .memberMatchingCondition(memberMatchingCondition)
                    .build();
        }
    }

    @Data
    public static class EditMember {
        private String nickname;
        private Member.Gender gender;
        private Member.Mbti mbti;

        @Builder
        public EditMember(
                String nickname,
                Member.Gender gender,
                Member.Mbti mbti
        ) {
            this.nickname = nickname;
            this.gender = gender;
            this.mbti = mbti;
        }

        public void update(Member member) {
            member.update(nickname, gender, mbti);
        }
    }
}
