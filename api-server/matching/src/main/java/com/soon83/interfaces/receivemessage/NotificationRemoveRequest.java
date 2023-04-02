package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NotificationRemoveRequest(
        @NotNull(message = "필수값")
        Long targetMemberId
) {
    public NotificationCommand.DeleteNotification toDeleteNotificationCommand(Long messageNotificationId) {
        return NotificationCommand.DeleteNotification.builder()
                .targetMemberId(targetMemberId)
                .messageNotificationId(messageNotificationId)
                .build();
    }
}
