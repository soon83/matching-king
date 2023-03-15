package com.soon83.domain.message;

import com.soon83.domain.message.reply.MessageReply;

public interface MessageReader {
    Message readById(Long messageId);
    MessageReply findLatelyMessageReplyById(Long messageId);
    void checkMessageLimit(Long memberId, int sendMessageCount);
}
