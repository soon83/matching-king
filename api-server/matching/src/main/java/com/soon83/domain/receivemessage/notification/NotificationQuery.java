package com.soon83.domain.receivemessage.notification;

import lombok.Builder;
import lombok.Data;

public class NotificationQuery {

    @Data
    public static class Main {
        private final Long id;
        private boolean isRead;
        private boolean isDeleted;

        @Builder
        public Main(
                Long id,
                boolean isRead,
                boolean isDeleted
        ) {
            this.id = id;
            this.isRead = isRead;
            this.isDeleted = isDeleted;
        }

        public Main(Notification entity) {
            this.id = entity.getId();
            this.isRead = entity.isRead();
            this.isDeleted = entity.isDeleted();
        }
    }
}
