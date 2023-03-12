package com.soon83.domain.message;

import lombok.Builder;
import lombok.Data;

public class MessageQuery {

    @Data
    public static class SearchCondition {
        private String content;
        private Long senderId;

        @Builder
        public SearchCondition(
                String content,
                Long senderId
        ) {
            this.content = content;
            this.senderId = senderId;
        }
    }

    @Data
    public static class Main {
        private final Long id;
        private final String content;
        private final Long senderId;

        @Builder
        public Main(
                Long id,
                String content,
                Long senderId
        ) {
            this.id = id;
            this.content = content;
            this.senderId = senderId;
        }

        public Main(Message entity) {
            this.id = entity.getId();
            this.content = entity.getContent();
            this.senderId = entity.getSender().getId();
        }
    }
}
