package com.soon83.domain.receivemessage;

import com.soon83.domain.receivemessage.reply.MessageReply;

import java.util.List;

public interface ReceiveMessageReader {
    ReceiveMessage readById(Long receiveMessageId);

    List<ReceiveMessage> searchNotificationsOfTargetMember(Long targetMemberId);

    List<ReceiveMessage> searchReceiveMessagesOfTargetMember(Long targetMemberId);

    MessageReply findLatelyMessageReplyByMessageId(Long messageId);
}
