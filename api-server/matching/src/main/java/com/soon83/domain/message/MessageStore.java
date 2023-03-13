package com.soon83.domain.message;

import com.soon83.domain.message.notification.MessageNotification;

import java.util.List;

public interface MessageStore {
    Message create(Message message);
    List<MessageNotification> create(List<MessageNotification> messageNotifications);
}
