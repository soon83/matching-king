package com.soon83.domain.member;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import lombok.Builder;
import lombok.Data;

public class MemberCommand {

    @Data
    public static class CreateMember {
        private String email;
        private String nickname;
        private int age;
        private Member.Gender gender;
        private Member.Mbti mbti;
        private Member.Type type = Member.Type.FREE;
        private Member.Role role = Member.Role.MEMBER;

        @Builder
        public CreateMember(
                String email,
                String nickname,
                int age,
                Member.Gender gender,
                Member.Mbti mbti
        ) {
            this.email = email;
            this.nickname = nickname;
            this.age = age;
            this.gender = gender;
            this.mbti = mbti;
        }

        public Member toEntity(
                Limit limit,
                MatchingCondition matchingCondition
        ) {
            return Member.builder()
                    .email(email)
                    .nickname(nickname)
                    .age(age)
                    .gender(gender)
                    .mbti(mbti)
                    .type(type)
                    .role(role)
                    .limit(limit)
                    .matchingCondition(matchingCondition)
                    .build();
        }
    }

    @Data
    public static class EditMember {
        private String nickname;
        private int age;
        private Member.Gender gender;
        private Member.Mbti mbti;

        @Builder
        public EditMember(
                String nickname,
                int age,
                Member.Gender gender,
                Member.Mbti mbti
        ) {
            this.nickname = nickname;
            this.age = age;
            this.gender = gender;
            this.mbti = mbti;
        }

        public void update(Member member) {
            member.update(nickname, age, gender, mbti);
        }
    }
}
