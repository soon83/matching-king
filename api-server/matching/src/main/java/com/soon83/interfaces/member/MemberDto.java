package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

public class MemberDto {
    @Data
    public static class RegisterRequest {
        @Email(message = "올바른 이메일 형식을 입력해주세요")
        @NotBlank(message = "필수값")
        private String memberEmail;
        @NotBlank(message = "필수값")
        private String memberNickname;
        @NotNull(message = "필수값")
        private Member.Gender memberGender;
        @NotNull(message = "필수값")
        private Member.MBTI memberMbti;
    }

    @Data
    public static class EditRequest {
        private String memberNickname;
        private Member.Gender memberGender;
        private Member.MBTI memberMbti;
    }

    @Data
    public static class CreateResponse {
        private final Long memberId;

        @Builder
        public CreateResponse(Long memberId) {
            this.memberId = memberId;
        }
    }

    @Data
    public static class Main {
        private final Long memberId;
        private final String memberEmail;
        private final String memberNickname;
        private final Member.Gender memberGender;
        private final Member.MBTI memberMbti;
        private final Member.Type memberType;
        private final Member.Role memberRole;

        @Builder
        public Main(
                Long memberId,
                String memberEmail,
                String memberNickname,
                Member.Gender memberGender,
                Member.MBTI memberMbti,
                Member.Type memberType,
                Member.Role memberRole
        ) {
            this.memberId = memberId;
            this.memberEmail = memberEmail;
            this.memberNickname = memberNickname;
            this.memberGender = memberGender;
            this.memberMbti = memberMbti;
            this.memberType = memberType;
            this.memberRole = memberRole;
        }
    }
}
