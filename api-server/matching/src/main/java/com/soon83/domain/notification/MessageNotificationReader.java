package com.soon83.domain.notification;

import java.util.List;

public interface MessageNotificationReader {
    List<MessageNotification> searchMessageNotificationsOfMember(Long targetMemberId);
}
