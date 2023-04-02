package com.soon83.application;

import com.soon83.domain.receivemessage.ReceiveMessageService;
import com.soon83.domain.receivemessage.notification.NotificationDeleteCommand;
import com.soon83.domain.receivemessage.notification.NotificationUpdateToReadCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationApplication {
    private final ReceiveMessageService receiveMessageService;

    public void changeToReadNotification(Long receiveMessageId, NotificationUpdateToReadCommand command) {
        receiveMessageService.changeToReadNotification(receiveMessageId, command);
    }

    public void removeNotification(Long receiveMessageId, NotificationDeleteCommand command) {
        receiveMessageService.removeReceiveMessageNotification(receiveMessageId, command);
    }
}
