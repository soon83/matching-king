package com.soon83.interfaces.messagenotification;

import com.soon83.domain.notification.MessageNotificationQuery;
import lombok.Builder;
import lombok.Data;

public class MessageNotificationDto {

    @Data
    public static class Main {
        private final Long messageNotificationId;
        private final boolean messageNotificationIsRead;
        private final boolean messageNotificationIsDeleted;
        private final Long targetMemberId;
        private final Long messageId;

        @Builder
        public Main(
                Long messageNotificationId,
                boolean messageNotificationIsRead,
                boolean messageNotificationIsDeleted,
                Long targetMemberId,
                Long messageId
        ) {
            this.messageNotificationId = messageNotificationId;
            this.messageNotificationIsRead = messageNotificationIsRead;
            this.messageNotificationIsDeleted = messageNotificationIsDeleted;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
        }

        public Main(MessageNotificationQuery.Main main) {
            this.messageNotificationId = main.getId();
            this.messageNotificationIsRead = main.isRead();
            this.messageNotificationIsDeleted = main.isDeleted();
            this.targetMemberId = main.getTargetMemberId();
            this.messageId = main.getMessageId();
        }
    }
}
