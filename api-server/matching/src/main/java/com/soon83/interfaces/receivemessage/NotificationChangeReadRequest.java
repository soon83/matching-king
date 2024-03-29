package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationUpdateToReadCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NotificationChangeReadRequest(
        @NotNull(message = "필수값")
        Long targetMemberId
) {
    public NotificationUpdateToReadCommand toUpdateNotificationCommand(Long messageNotificationId) {
        return NotificationUpdateToReadCommand.builder()
                .targetMemberId(targetMemberId)
                .messageNotificationId(messageNotificationId)
                .build();
    }
}
