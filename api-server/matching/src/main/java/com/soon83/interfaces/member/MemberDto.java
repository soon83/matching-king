package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberCommand;
import com.soon83.domain.member.MemberQuery;
import com.soon83.interfaces.limit.LimitDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

public class MemberDto {

    @Data
    public static class RegisterRequest {
        @NotBlank(message = "필수값")
        @Email(message = "올바른 이메일 형식을 입력해주세요")
        private String memberEmail;
        @NotBlank(message = "필수값")
        private String memberNickname;
        @NotNull(message = "필수값")
        @Min(value = 5, message = "최소 5 이상")
        @Max(value = 100, message = "최대 100 이하")
        private int memberAge = 0;
        @NotNull(message = "필수값")
        private Member.Gender memberGender;
        @NotNull(message = "필수값")
        private Member.Mbti memberMbti;
        @Valid
        @NotNull(message = "필수값")
        private MatchingConditionDto.RegisterRequest memberMatchingCondition;

        public MemberCommand.CreateMember toCreateMemberCommand() {
            return MemberCommand.CreateMember.builder()
                    .email(memberEmail)
                    .nickname(memberNickname)
                    .age(memberAge)
                    .gender(memberGender)
                    .mbti(memberMbti)
                    .build();
        }
    }

    @Data
    public static class EditRequest {
        @NotBlank(message = "필수값")
        private String memberNickname;
        @NotNull(message = "필수값")
        @Min(value = 5, message = "최소 5 이상")
        @Max(value = 100, message = "최대 100 이하")
        private int memberAge = 0;
        @NotNull(message = "필수값")
        private Member.Gender memberGender;
        @NotNull(message = "필수값")
        private Member.Mbti memberMbti;
        @Valid
        @NotNull(message = "필수값")
        private MatchingConditionDto.EditRequest memberMatchingCondition;

        public MemberCommand.EditMember toEditMemberCommand() {
            return MemberCommand.EditMember.builder()
                    .nickname(memberNickname)
                    .age(memberAge)
                    .gender(memberGender)
                    .mbti(memberMbti)
                    .build();
        }
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
    public static class SearchCondition {
        @Email(message = "올바른 이메일 형식을 입력해주세요")
        private String memberEmail;
        private String memberNickname;
        private int memberAge;
        private Member.Gender memberGender;
        private Member.Mbti memberMbti;
        private Member.Type memberType;
        private Member.Role memberRole;

        public MemberQuery.SearchCondition toSearchMemberCondition() {
            return MemberQuery.SearchCondition.builder()
                    .email(memberEmail)
                    .nickname(memberNickname)
                    .age(memberAge)
                    .gender(memberGender)
                    .mbti(memberMbti)
                    .type(memberType)
                    .role(memberRole)
                    .build();
        }
    }

    @Data
    public static class Main {
        private final Long memberId;
        private final String memberEmail;
        private final String memberNickname;
        private final int memberAge;
        private final Member.Gender memberGender;
        private final Member.Mbti memberMbti;
        private final Member.Type memberType;
        private final Member.Role memberRole;

        @Builder
        public Main(
                Long memberId,
                String memberEmail,
                String memberNickname,
                int memberAge,
                Member.Gender memberGender,
                Member.Mbti memberMbti,
                Member.Type memberType,
                Member.Role memberRole
        ) {
            this.memberId = memberId;
            this.memberEmail = memberEmail;
            this.memberNickname = memberNickname;
            this.memberAge = memberAge;
            this.memberGender = memberGender;
            this.memberMbti = memberMbti;
            this.memberType = memberType;
            this.memberRole = memberRole;
        }

        @Builder
        public Main(MemberQuery.Main query) {
            this.memberId = query.getId();
            this.memberEmail = query.getEmail();
            this.memberNickname = query.getNickname();
            this.memberAge = query.getAge();
            this.memberGender = query.getGender();
            this.memberMbti = query.getMbti();
            this.memberType = query.getType();
            this.memberRole = query.getRole();
        }
    }

    @Data
    public static class Detail {
        private final Long memberId;
        private final String memberEmail;
        private final String memberNickname;
        private final int memberAge;
        private final Member.Gender memberGender;
        private final Member.Mbti memberMbti;
        private final Member.Type memberType;
        private final Member.Role memberRole;
        private final LimitDto.Main memberLimit;
        private final MatchingConditionDto.Main memberMatchingCondition;

        @Builder
        public Detail(MemberQuery.Detail query) {
            this.memberId = query.getId();
            this.memberEmail = query.getEmail();
            this.memberNickname = query.getNickname();
            this.memberAge = query.getAge();
            this.memberGender = query.getGender();
            this.memberMbti = query.getMbti();
            this.memberType = query.getType();
            this.memberRole = query.getRole();
            this.memberLimit = new LimitDto.Main(query.getLimit());
            this.memberMatchingCondition = new MatchingConditionDto.Main(query.getMatchingCondition());
        }
    }
}
