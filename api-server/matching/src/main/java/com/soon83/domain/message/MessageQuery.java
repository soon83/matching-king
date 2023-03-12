package com.soon83.domain.message;

import lombok.Builder;
import lombok.Data;

public class MessageQuery {

    @Data
    public static class SearchCondition {
        private String content;
        private Long writerMemberId;

        @Builder
        public SearchCondition(
                String content,
                Long writerMemberId
        ) {
            this.content = content;
            this.writerMemberId = writerMemberId;
        }
    }

    @Data
    public static class Main {
        private final Long id;
        private final String content;
        private final Long writerMemberId;

        @Builder
        public Main(
                Long id,
                String content,
                Long writerMemberId
        ) {
            this.id = id;
            this.content = content;
            this.writerMemberId = writerMemberId;
        }

        public Main(Message entity) {
            this.id = entity.getId();
            this.content = entity.getContent();
            this.writerMemberId = entity.getWriterMember().getId();
        }
    }
}
