package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationDeleteCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NotificationRemoveRequest(
        @NotNull(message = "필수값")
        Long targetMemberId
) {
    public NotificationDeleteCommand toDeleteNotificationCommand(Long messageNotificationId) {
        return NotificationDeleteCommand.builder()
                .targetMemberId(targetMemberId)
                .messageNotificationId(messageNotificationId)
                .build();
    }
}
