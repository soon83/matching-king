package com.soon83.domain.limit.model;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;

public class LimitQuery {

    @Data
    public static class SearchCondition {
        private Member.Type memberType;

        @Builder
        public SearchCondition(Member.Type memberType) {
            this.memberType = memberType;
        }
    }

    @Data
    public static class Main {
        private final Long id;
        private final Member.Type memberType;
        private final int sendMessageCount;

        @Builder
        public Main(
                Long id,
                Member.Type memberType,
                int sendMessageCount
        ) {
            this.id = id;
            this.memberType = memberType;
            this.sendMessageCount = sendMessageCount;
        }

        public Main(Limit entity) {
            this.id = entity.getId();
            this.memberType = entity.getMemberType();
            this.sendMessageCount = entity.getSendMessageCount();
        }
    }
}
