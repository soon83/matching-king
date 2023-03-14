package com.soon83.domain.receivemessage.notification;

public interface NotificationService {
    void changeToRead(Long memberId, Long receiveMessageId, Long messageNotificationId);
    void removeNotification(Long memberId, Long receiveMessageId, Long messageNotificationId);
}
