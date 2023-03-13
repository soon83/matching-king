package com.soon83.domain.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageNotificationServiceImpl implements MessageNotificationService {

    private final MessageNotificationReader messageNotificationReader;

    @Override
    @Transactional(readOnly = true)
    public List<MessageNotificationQuery.Main> searchMessageNotificationsOfMember(Long targetMemberId) {
        return messageNotificationReader.searchMessageNotificationsOfMember(targetMemberId).stream()
                .map(MessageNotificationQuery.Main::new)
                .toList();
    }
}
