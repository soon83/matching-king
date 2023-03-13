package com.soon83.infrastructure.messagenotification;

import com.soon83.domain.notification.MessageNotification;
import com.soon83.domain.notification.MessageNotificationReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageNotificationReaderImpl implements MessageNotificationReader {

    private final MessageNotificationRepository messageNotificationRepository;

    @Override
    public List<MessageNotification> searchMessageNotificationsOfMember(Long targetMemberId) {
        return messageNotificationRepository.findByTargetMemberId(targetMemberId);
    }
}
