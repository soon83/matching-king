package com.soon83.interfaces.messagemeta;

import com.soon83.domain.messagemeta.MessageMetaQuery;
import com.soon83.domain.messagemeta.notification.MessageNotification;
import lombok.Builder;
import lombok.Data;

public class MessageMetaDto {

    @Data
    public static class Main {
        private final Long messageMetaId;
        private final boolean messageMetaIsDeleted;
        private final Long targetMemberId;
        private final Long messageId;

        @Builder
        public Main(
                Long messageMetaId,
                boolean messageMetaIsDeleted,
                Long targetMemberId,
                Long messageId
        ) {
            this.messageMetaId = messageMetaId;
            this.messageMetaIsDeleted = messageMetaIsDeleted;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
        }

        public Main(MessageMetaQuery.Main main) {
            this.messageMetaId = main.getId();
            this.messageMetaIsDeleted = main.isDeleted();
            this.targetMemberId = main.getTargetMemberId();
            this.messageId = main.getMessageId();
        }
    }
}
