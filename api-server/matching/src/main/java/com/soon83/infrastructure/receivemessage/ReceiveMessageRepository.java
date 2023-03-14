package com.soon83.infrastructure.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiveMessageRepository extends JpaRepository<ReceiveMessage, Long>, ReceiveMessageRepositoryQuerydsl {
}
