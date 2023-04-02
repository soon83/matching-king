package com.soon83.domain.receivemessage;

import com.soon83.domain.receivemessage.reply.MessageReply;

public interface ReceiveMessageStore {
    MessageReply create(MessageReply messageReply);
}
