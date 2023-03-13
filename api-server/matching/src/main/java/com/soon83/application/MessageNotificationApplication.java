package com.soon83.application;

import com.soon83.domain.notification.MessageNotificationQuery;
import com.soon83.domain.notification.MessageNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageNotificationApplication {

    private final MessageNotificationService messageNotificationService;

    public List<MessageNotificationQuery.Main> searchMessageNotificationsOfMember(Long targetMemberId) {
        return messageNotificationService.searchMessageNotificationsOfMember(targetMemberId);
    }
}
