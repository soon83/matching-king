package com.soon83.application;

import com.soon83.domain.receivemessage.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationApplication {

    private final NotificationService notificationService;

    public void changeToRead(Long memberId, Long receiveMessageId, Long messageNotificationId) {
        notificationService.changeToRead(memberId, receiveMessageId, messageNotificationId);
    }

    public void removeNotification(Long memberId, Long receiveMessageId, Long messageNotificationId) {
        notificationService.removeNotification(memberId, receiveMessageId, messageNotificationId);
    }
}
