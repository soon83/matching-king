package com.soon83.domain.messagemeta;

import lombok.Builder;
import lombok.Data;

public class MessageMetaQuery {

    @Data
    public static class Main {
        private final Long id;
        private final boolean isDeleted;
        private final Long targetMemberId;
        private final Long messageId;

        @Builder
        public Main(
                Long id,
                boolean isDeleted,
                Long targetMemberId,
                Long messageId
        ) {
            this.id = id;
            this.isDeleted = isDeleted;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
        }

        public Main(MessageMeta entity) {
            this.id = entity.getId();
            this.isDeleted = entity.isDeleted();
            this.targetMemberId = entity.getTargetMember().getId();
            this.messageId = entity.getMessage().getId();
        }
    }
}
