package com.soon83.infrastructure.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessage;

import java.util.List;

public interface ReceiveMessageRepositoryQuerydsl {
    List<ReceiveMessage> searchNotificationsOfTargetMember(Long targetMemberId);
    List<ReceiveMessage> searchReceiveMessagesOfTargetMember(Long targetMemberId);
}