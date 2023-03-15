package com.soon83.domain.receivemessage.notification;

import com.soon83.domain.receivemessage.ReceiveMessage;
import com.soon83.domain.receivemessage.ReceiveMessageReader;
import com.soon83.exception.receivemessage.NotMyReceiveMessageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final ReceiveMessageReader receiveMessageReader;

    @Override
    @Transactional
    public void changeToRead(Long memberId, Long receiveMessageId, Long messageNotificationId) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        checkMyReceiveMessage(memberId, receiveMessage);
        receiveMessage.getNotification().changeToRead(true);
    }

    @Override
    @Transactional
    public void removeNotification(Long memberId, Long receiveMessageId, Long messageNotificationId) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        checkMyReceiveMessageNotification(messageNotificationId, receiveMessage);
        receiveMessage.getNotification().delete();
    }

    private static void checkMyReceiveMessage(Long memberId, ReceiveMessage receiveMessage) {
        if (!Objects.equals(receiveMessage.getTargetMember().getId(), memberId)) {
            throw new NotMyReceiveMessageException();
        }
    }

    private static void checkMyReceiveMessageNotification(Long messageNotificationId, ReceiveMessage receiveMessage) {
        if (!Objects.equals(receiveMessage.getNotification().getId(), messageNotificationId)) {
            throw new NotMyReceiveMessageException();
        }
    }
}
