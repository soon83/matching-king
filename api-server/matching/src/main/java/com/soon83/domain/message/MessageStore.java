package com.soon83.domain.message;

import com.soon83.domain.message.reply.MessageReply;

public interface MessageStore {
    Message create(Message message);
    MessageReply create(MessageReply messageReply);
}
