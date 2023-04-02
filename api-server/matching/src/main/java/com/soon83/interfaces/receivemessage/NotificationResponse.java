package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationQuery;
import lombok.Builder;

@Builder
public record NotificationResponse(
        Long messageNotificationId,
        boolean isRead,
        boolean isDeleted
) {
    public NotificationResponse(NotificationQuery query) {
        this(
                query.id(),
                query.isRead(),
                query.isDeleted()
        );
    }
}
