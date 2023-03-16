package com.soon83.domain.receivemessage.notification;

import lombok.Builder;
import lombok.Data;

public class NotificationCommand {

    @Data
    public static class UpdateToRead {
        private Long targetMemberId;
        private Long messageNotificationId;

        @Builder
        public UpdateToRead(
                Long targetMemberId,
                Long messageNotificationId
        ) {
            this.targetMemberId = targetMemberId;
            this.messageNotificationId = messageNotificationId;
        }
    }

    @Data
    public static class DeleteNotification {
        private Long targetMemberId;
        private Long messageNotificationId;

        @Builder
        public DeleteNotification(
                Long targetMemberId,
                Long messageNotificationId
        ) {
            this.targetMemberId = targetMemberId;
            this.messageNotificationId = messageNotificationId;
        }
    }
}
