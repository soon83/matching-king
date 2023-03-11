package com.soon83.domain.member.condition.model;

import com.soon83.domain.member.condition.MemberCondition;
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
}
