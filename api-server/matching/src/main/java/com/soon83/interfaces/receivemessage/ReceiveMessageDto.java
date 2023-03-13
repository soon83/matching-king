package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import lombok.Builder;
import lombok.Data;

public class ReceiveMessageDto {

    @Data
    public static class Main {
        private final Long receiveMessageId;
        private final boolean isDeleted;
        private final Long targetMemberId;
        private final Long messageId;

        @Builder
        public Main(
                Long receiveMessageId,
                boolean isDeleted,
                Long targetMemberId,
                Long messageId
        ) {
            this.receiveMessageId = receiveMessageId;
            this.isDeleted = isDeleted;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
        }

        public Main(ReceiveMessageQuery.Main main) {
            this.receiveMessageId = main.getId();
            this.isDeleted = main.isDeleted();
            this.targetMemberId = main.getTargetMemberId();
            this.messageId = main.getMessageId();
        }
    }
}
