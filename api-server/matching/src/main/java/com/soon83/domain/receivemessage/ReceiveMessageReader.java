package com.soon83.domain.receivemessage;

import java.util.List;

public interface ReceiveMessageReader {
    ReceiveMessage readById(Long receiveMessageId);
    List<ReceiveMessage> searchNotificationsOfTargetMember(Long targetMemberId);
    List<ReceiveMessage> searchReceiveMessagesOfTargetMember(Long targetMemberId);
}
