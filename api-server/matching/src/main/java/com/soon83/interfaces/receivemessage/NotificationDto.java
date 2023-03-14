package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationQuery;
import lombok.Builder;
import lombok.Data;

public class NotificationDto {

    @Data
    public static class Main {
        private final Long messageNotificationId;
        private boolean isRead;
        private boolean isDeleted;

        @Builder
        public Main(
                Long messageNotificationId,
                boolean isRead,
                boolean isDeleted
        ) {
            this.messageNotificationId = messageNotificationId;
            this.isRead = isRead;
            this.isDeleted = isDeleted;
        }

        public Main(NotificationQuery.Main query) {
            this.messageNotificationId = query.getId();
            this.isRead = query.isRead();
            this.isDeleted = query.isDeleted();
        }
    }
}
