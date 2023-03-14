package com.soon83.domain.receivemessage;

import java.util.List;

public interface ReceiveMessageReader {
    List<ReceiveMessage> searchReceiveMessagesNotificationsOfTargetMember(Long targetMemberId);
    List<ReceiveMessage> searchReceiveMessagesOfTargetMember(Long targetMemberId);
}
