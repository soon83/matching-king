package com.soon83.infrastructure.message;

import com.soon83.domain.message.Message;
import com.soon83.domain.message.MessageStore;
import com.soon83.domain.message.notification.MessageNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageStoreImpl implements MessageStore {

    private final MessageRepository messageRepository;
    private final MessageNotificationRepository messageNotificationRepository;

    @Override
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<MessageNotification> create(List<MessageNotification> messageNotifications) {
        return messageNotificationRepository.saveAll(messageNotifications);
    }
}
