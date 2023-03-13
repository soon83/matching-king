package com.soon83.domain.receivemessage;

import lombok.Builder;
import lombok.Data;

public class ReceiveMessageQuery {

    @Data
    public static class Main {
        private final Long id;
        private final Long targetMemberId;
        private final Long messageId;

        @Builder
        public Main(
                Long id,
                Long targetMemberId,
                Long messageId
        ) {
            this.id = id;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
        }

        public Main(ReceiveMessage entity) {
            this.id = entity.getId();
            this.targetMemberId = entity.getTargetMember().getId();
            this.messageId = entity.getMessage().getId();
        }
    }
}
