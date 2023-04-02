package com.soon83.domain.receivemessage.notification;

import lombok.Builder;

@Builder
public record NotificationQuery(
        Long id,
        boolean isRead,
        boolean isDeleted
) {
    public NotificationQuery(Notification entity) {
        this(
                entity.getId(),
                entity.isRead(),
                entity.isDeleted()
        );
    }
}
