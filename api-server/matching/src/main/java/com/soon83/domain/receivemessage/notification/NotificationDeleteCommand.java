package com.soon83.domain.receivemessage.notification;

import lombok.Builder;

@Builder
public record NotificationDeleteCommand(
        Long targetMemberId,
        Long messageNotificationId
) {
}
