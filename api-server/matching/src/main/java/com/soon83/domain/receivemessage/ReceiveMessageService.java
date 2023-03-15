package com.soon83.domain.receivemessage;

import java.util.List;

public interface ReceiveMessageService {
    List<ReceiveMessageQuery.Notification> searchNotificationsOfTargetMember(Long targetMemberId);
    List<ReceiveMessageQuery.Main> searchReceiveMessagesOfTargetMember(Long targetMemberId);
    void removeReceiveMessage(Long memberId, Long receiveMessageId);
}
