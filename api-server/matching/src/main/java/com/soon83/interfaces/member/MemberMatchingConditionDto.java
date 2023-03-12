package com.soon83.interfaces.member;

import com.soon83.domain.member.matchingcondition.MemberMatchingConditionQuery;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

public class MemberMatchingConditionDto {

    @Data
    public static class RegisterRequest {
        @NotNull(message = "필수값")
        @Min(value = 1, message = "1 이상")
        private int minAge = 0;
        @NotNull(message = "필수값")
        @Min(value = 1, message = "1 이상")
        private int maxAge = 0;
        @NotNull(message = "필수값")
        private Gender gender;
        @NotNull(message = "필수값")
        private Mbti mbti;
    }

    @Data
    public static class EditRequest {
        @NotNull(message = "필수값")
        @Min(value = 1, message = "1 이상")
        private int minAge = 0;
        @NotNull(message = "필수값")
        @Min(value = 1, message = "1 이상")
        private int maxAge = 0;
        @NotNull(message = "필수값")
        private Gender gender;
        @NotNull(message = "필수값")
        private Mbti mbti;
    }

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

        public Main(MemberMatchingConditionQuery.Main main) {
            this.id = main.getId();
            this.minAge = main.getMinAge();
            this.maxAge = main.getMaxAge();
            this.gender = main.getGender();
            this.mbti = main.getMbti();
        }
    }
}
