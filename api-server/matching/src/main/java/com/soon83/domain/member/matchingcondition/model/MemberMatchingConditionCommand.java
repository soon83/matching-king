package com.soon83.domain.member.matchingcondition.model;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.matchingcondition.MemberMatchingCondition;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import lombok.Builder;
import lombok.Data;

public class MemberMatchingConditionCommand {

    @Data
    public static class CreateMemberMatchingCondition {
        private int minAge;
        private int maxAge;
        private Gender gender;
        private Mbti mbti;

        @Builder
        public CreateMemberMatchingCondition(int minAge, int maxAge, Gender gender, Mbti mbti) {
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.gender = gender;
            this.mbti = mbti;
        }

        public MemberMatchingCondition toEntity() {
            return MemberMatchingCondition.builder()
                    .minAge(minAge)
                    .maxAge(maxAge)
                    .gender(gender)
                    .mbti(mbti)
                    .build();
        }
    }

    @Data
    public static class EditMemberMatchingCondition {
        private int minAge;
        private int maxAge;
        private Gender gender;
        private Mbti mbti;

        @Builder
        public EditMemberMatchingCondition(
                int minAge,
                int maxAge,
                Gender gender,
                Mbti mbti
        ) {
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.gender = gender;
            this.mbti = mbti;
        }

        public void update(Member member) {
            member.getMemberMatchingCondition().update(minAge, maxAge, gender, mbti);
        }
    }
}
