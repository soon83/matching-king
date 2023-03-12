package com.soon83.interfaces.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.condition.MemberConditionCommand;
import com.soon83.domain.member.matchingcondition.MemberMatchingConditionCommand;
import com.soon83.domain.member.MemberCommand;
import com.soon83.domain.member.MemberQuery;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
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
        private Member.Gender memberGender;
        @NotNull(message = "필수값")
        private Member.Mbti memberMbti;
        @NotNull(message = "필수값")
        @Min(value = 5, message = "최소 5 이상")
        @Max(value = 100, message = "최대 100 이하")
        private int memberAge = 0;
        @Valid
        @NotNull(message = "필수값")
        private MemberMatchingConditionDto.RegisterRequest memberMatchingCondition;

        public MemberCommand.CreateMember toCreateMemberCommand() {
            return MemberCommand.CreateMember.builder()
                    .email(memberEmail)
                    .nickname(memberNickname)
                    .gender(memberGender)
                    .mbti(memberMbti)
                    .build();
        }

        public MemberConditionCommand.CreateMemberCondition toCreateMemberConditionCommand() {
            return MemberConditionCommand.CreateMemberCondition.builder()
                    .age(memberAge)
                    .gender(new Gender(memberGender))
                    .mbti(new Mbti(memberMbti))
                    .build();
        }

        public MemberMatchingConditionCommand.CreateMemberMatchingCondition toCreateMemberMatchingConditionCommand() {
            return MemberMatchingConditionCommand.CreateMemberMatchingCondition.builder()
                    .minAge(memberMatchingCondition.getMinAge())
                    .maxAge(memberMatchingCondition.getMaxAge())
                    .gender(memberMatchingCondition.getGender())
                    .mbti(memberMatchingCondition.getMbti())
                    .build();
        }
    }

    @Data
    public static class EditRequest {
        @NotBlank(message = "필수값")
        private String memberNickname;
        @NotNull(message = "필수값")
        private Member.Gender memberGender;
        @NotNull(message = "필수값")
        private Member.Mbti memberMbti;
        @NotNull(message = "필수값")
        @Min(value = 5, message = "최소 5 이상")
        @Max(value = 100, message = "최대 100 이하")
        private int memberAge = 0;
        @Valid
        @NotNull(message = "필수값")
        private MemberMatchingConditionDto.RegisterRequest memberMatchingCondition;

        public MemberCommand.EditMember toEditMemberCommand() {
            return MemberCommand.EditMember.builder()
                    .nickname(memberNickname)
                    .gender(memberGender)
                    .mbti(memberMbti)
                    .build();
        }

        public MemberConditionCommand.EditMemberCondition toEditMemberConditionCommand() {
            return MemberConditionCommand.EditMemberCondition.builder()
                    .age(memberAge)
                    .gender(new Gender(memberGender))
                    .mbti(new Mbti(memberMbti))
                    .build();
        }

        public MemberMatchingConditionCommand.EditMemberMatchingCondition toEditMemberMatchingConditionCommand() {
            return MemberMatchingConditionCommand.EditMemberMatchingCondition.builder()
                    .minAge(memberMatchingCondition.getMinAge())
                    .maxAge(memberMatchingCondition.getMaxAge())
                    .gender(memberMatchingCondition.getGender())
                    .mbti(memberMatchingCondition.getMbti())
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
        private Member.Gender memberGender;
        private Member.Mbti memberMbti;
        private Member.Type memberType;
        private Member.Role memberRole;

        public MemberQuery.SearchCondition toSearchMemberCondition() {
            return MemberQuery.SearchCondition.builder()
                    .email(memberEmail)
                    .nickname(memberNickname)
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
        private final Member.Gender memberGender;
        private final Member.Mbti memberMbti;
        private final Member.Type memberType;
        private final Member.Role memberRole;

        @Builder
        public Main(
                Long memberId,
                String memberEmail,
                String memberNickname,
                Member.Gender memberGender,
                Member.Mbti memberMbti,
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

        @Builder
        public Main(MemberQuery.Main memberMain) {
            this.memberId = memberMain.getId();
            this.memberEmail = memberMain.getEmail();
            this.memberNickname = memberMain.getNickname();
            this.memberGender = memberMain.getGender();
            this.memberMbti = memberMain.getMbti();
            this.memberType = memberMain.getType();
            this.memberRole = memberMain.getRole();
        }
    }

    @Data
    public static class Detail {
        private final Long memberId;
        private final String memberEmail;
        private final String memberNickname;
        private final Member.Gender memberGender;
        private final Member.Mbti memberMbti;
        private final Member.Type memberType;
        private final Member.Role memberRole;
        private final LimitDto.Main memberLimit;
        private final int memberAge;
        private final MemberMatchingConditionDto.Main memberMatchingCondition;

        @Builder
        public Detail(MemberQuery.Detail detail) {
            this.memberId = detail.getId();
            this.memberEmail = detail.getEmail();
            this.memberNickname = detail.getNickname();
            this.memberGender = detail.getGender();
            this.memberMbti = detail.getMbti();
            this.memberType = detail.getType();
            this.memberRole = detail.getRole();
            this.memberLimit = new LimitDto.Main(detail.getLimit());
            this.memberAge = new MemberConditionDto.Main(detail.getMemberCondition()).getAge();
            this.memberMatchingCondition = new MemberMatchingConditionDto.Main(detail.getMemberMatchingCondition());
        }
    }
}
