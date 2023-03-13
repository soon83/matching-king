package com.soon83.infrastructure.messagenotification;

import com.soon83.domain.notification.MessageNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageNotificationRepository extends JpaRepository<MessageNotification, Long> {
    List<MessageNotification> findByTargetMemberId(Long targetMemberId);
}
