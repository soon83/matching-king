package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NotificationChangeReadRequest(
        @NotNull(message = "필수값")
        Long targetMemberId
) {
    public NotificationCommand.UpdateToRead toUpdateNotificationCommand(Long messageNotificationId) {
        return NotificationCommand.UpdateToRead.builder()
                .targetMemberId(targetMemberId)
                .messageNotificationId(messageNotificationId)
                .build();
    }
}
