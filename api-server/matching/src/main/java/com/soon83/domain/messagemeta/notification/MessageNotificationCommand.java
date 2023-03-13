package com.soon83.domain.messagemeta.notification;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import lombok.Builder;
import lombok.Data;

public class MessageNotificationCommand {

    @Data
    public static class CreateMatchingCondition {
        private int minAge;
        private int maxAge;
        private Gender gender;
        private Mbti mbti;

        @Builder
        public CreateMatchingCondition(int minAge, int maxAge, Gender gender, Mbti mbti) {
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.gender = gender;
            this.mbti = mbti;
        }

        public MatchingCondition toEntity() {
            return MatchingCondition.builder()
                    .minAge(minAge)
                    .maxAge(maxAge)
                    .gender(gender)
                    .mbti(mbti)
                    .build();
        }
    }

    @Data
    public static class EditMatchingCondition {
        private int minAge;
        private int maxAge;
        private Gender gender;
        private Mbti mbti;

        @Builder
        public EditMatchingCondition(
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
            member.getMatchingCondition().update(minAge, maxAge, gender, mbti);
        }
    }
}
