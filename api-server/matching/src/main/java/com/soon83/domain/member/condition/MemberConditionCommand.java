package com.soon83.domain.member.condition;

import com.soon83.domain.member.Member;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import lombok.Builder;
import lombok.Data;

public class MemberConditionCommand {

    @Data
    public static class CreateMemberCondition {
        private int age;
        private Gender gender;
        private Mbti mbti;

        @Builder
        public CreateMemberCondition(
                int age,
                Gender gender,
                Mbti mbti
        ) {
            this.age = age;
            this.gender = gender;
            this.mbti = mbti;
        }

        public MemberCondition toEntity() {
            return MemberCondition.builder()
                    .age(age)
                    .gender(gender)
                    .mbti(mbti)
                    .build();
        }
    }

    @Data
    public static class EditMemberCondition {
        private int age;
        private Gender gender;
        private Mbti mbti;

        @Builder
        public EditMemberCondition(
                int age,
                Gender gender,
                Mbti mbti
        ) {
            this.age = age;
            this.gender = gender;
            this.mbti = mbti;
        }

        public void update(Member member) {
            member.getMemberCondition().update(age, gender, mbti);
        }
    }
}