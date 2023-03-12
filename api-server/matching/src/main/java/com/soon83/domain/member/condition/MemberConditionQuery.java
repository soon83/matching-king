package com.soon83.domain.member.condition;

import lombok.Builder;
import lombok.Data;

public class MemberConditionQuery {

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

        public Main(MemberCondition entity) {
            this.id = entity.getId();
            this.age = entity.getAge();
        }
    }
}
