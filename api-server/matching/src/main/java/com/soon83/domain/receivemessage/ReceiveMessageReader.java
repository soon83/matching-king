package com.soon83.domain.receivemessage;

import java.util.List;

public interface ReceiveMessageReader {
    List<ReceiveMessage> searchReceiveMessagesOfMember(Long targetMemberId);

}
