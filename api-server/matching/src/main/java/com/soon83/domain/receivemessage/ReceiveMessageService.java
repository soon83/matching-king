package com.soon83.domain.receivemessage;

import java.util.List;

public interface ReceiveMessageService {
    List<ReceiveMessageQuery.Main> searchReceiveMessagesOfMember(Long targetMemberId);
}
