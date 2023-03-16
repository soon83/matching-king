package com.soon83.application;

import com.soon83.domain.receivemessage.ReceiveMessageService;
import com.soon83.domain.receivemessage.notification.NotificationCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationApplication {

    private final ReceiveMessageService receiveMessageService;

    public void changeToReadNotification(Long receiveMessageId, NotificationCommand.UpdateToRead command) {
        receiveMessageService.changeToReadNotification(receiveMessageId, command);
    }

    public void removeNotification(Long receiveMessageId, NotificationCommand.DeleteNotification command) {
        receiveMessageService.removeReceiveMessageNotification(receiveMessageId, command);
    }
}
