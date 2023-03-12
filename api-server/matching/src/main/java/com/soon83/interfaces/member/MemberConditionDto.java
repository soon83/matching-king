package com.soon83.interfaces.member;

import com.soon83.domain.member.condition.MemberConditionQuery;
import lombok.Builder;
import lombok.Data;

public class MemberConditionDto {

    @Data
    public static class Main {
        private final Long id;
        private final int age;

        @Builder
        public Main(
                Long id,
                int age
        ) {
            this.id = id;
            this.age = age;
        }

        public Main(MemberConditionQuery.Main main) {
            this.id = main.getId();
            this.age = main.getAge();
        }
    }
}
