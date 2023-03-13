package com.soon83.domain.messagemeta.notification;

import com.soon83.domain.member.matchingcondition.MatchingCondition;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import lombok.Builder;
import lombok.Data;

public class MessageNotificationQuery {

    @Data
    public static class Main {
        private final Long id;
        private final int minAge;
        private final int maxAge;
        private final Gender gender;
        private final Mbti mbti;

        @Builder
        public Main(
                Long id,
                int minAge,
                int maxAge,
                Gender gender,
                Mbti mbti
        ) {
            this.id = id;
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.gender = gender;
            this.mbti = mbti;
        }

        public Main(MatchingCondition entity) {
            this.id = entity.getId();
            this.minAge = entity.getMinAge();
            this.maxAge = entity.getMaxAge();
            this.gender = entity.getGender();
            this.mbti = entity.getMbti();
        }
    }
}
