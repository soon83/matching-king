package com.soon83.domain.notification;

import java.util.List;

public interface MessageNotificationService {
    List<MessageNotificationQuery.Main> searchMessageNotificationsOfMember(Long targetMemberId);
}
