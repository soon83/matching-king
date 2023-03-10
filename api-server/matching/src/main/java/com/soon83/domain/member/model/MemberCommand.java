package com.soon83.domain.member.model;

import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;

public class MemberCommand {

    @Data
    public static class CreateMember {
        private String email;
        private String nickname;
        private Member.Gender gender;
        private Member.Mbti mbti;

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

        public Member toEntity() {
            return Member.builder()
                    .email(email)
                    .nickname(nickname)
                    .gender(gender)
                    .mbti(mbti)
                    .build();
        }
    }

    @Data
    public static class Main {
        private final Long id;
        private final String email;
        private final String nickname;
        private final Member.Gender gender;
        private final Member.Mbti mbti;
        private final Member.Type type;
        private final Member.Role role;

        @Builder
        public Main(
                Long id,
                String email,
                String nickname,
                Member.Gender gender,
                Member.Mbti mbti,
                Member.Type type,
                Member.Role role
        ) {
            this.id = id;
            this.email = email;
            this.nickname = nickname;
            this.gender = gender;
            this.mbti = mbti;
            this.type = type;
            this.role = role;
        }
    }
}
