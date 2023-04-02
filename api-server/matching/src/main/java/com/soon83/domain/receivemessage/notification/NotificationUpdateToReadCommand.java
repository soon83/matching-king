package com.soon83.domain.receivemessage.notification;

import lombok.Builder;

@Builder
public record NotificationUpdateToReadCommand(
        Long targetMemberId,
        Long messageNotificationId
) {
}
