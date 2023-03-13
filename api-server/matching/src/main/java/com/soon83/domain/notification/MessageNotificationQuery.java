package com.soon83.domain.notification;

import lombok.Builder;
import lombok.Data;

public class MessageNotificationQuery {

    @Data
    public static class Main {
        private final Long id;
        private final boolean isRead;
        private final boolean isDeleted;
        private final Long targetMemberId;
        private final Long messageId;

        @Builder
        public Main(
                Long id,
                boolean isRead,
                boolean isDeleted,
                Long targetMemberId,
                Long messageId
        ) {
            this.id = id;
            this.isRead = isRead;
            this.isDeleted = isDeleted;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
        }

        public Main(MessageNotification entity) {
            this.id = entity.getId();
            this.isRead = entity.isRead();
            this.isDeleted = entity.isDeleted();
            this.targetMemberId = entity.getTargetMember().getId();
            this.messageId = entity.getMessage().getId();
        }
    }
}
