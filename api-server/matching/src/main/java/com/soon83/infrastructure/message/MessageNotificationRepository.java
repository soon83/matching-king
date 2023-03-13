package com.soon83.infrastructure.message;

import com.soon83.domain.message.notification.MessageNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageNotificationRepository extends JpaRepository<MessageNotification, Long> {
}
