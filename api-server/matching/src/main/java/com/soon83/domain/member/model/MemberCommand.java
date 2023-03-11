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
