package com.soon83.domain.message;

import com.soon83.domain.message.reply.MessageReplyCommand;

public interface MessageService {
    Long registerMessage(MessageCommand.CreateMessage createMessageCommand);
    Long registerMessageReply(Long receiveMessageId, MessageReplyCommand.CreateReply createMessageReplyCommand);
}
