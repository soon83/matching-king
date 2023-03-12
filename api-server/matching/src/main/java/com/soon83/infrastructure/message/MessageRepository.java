package com.soon83.infrastructure.message;

import com.soon83.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryQuerydsl {
}
