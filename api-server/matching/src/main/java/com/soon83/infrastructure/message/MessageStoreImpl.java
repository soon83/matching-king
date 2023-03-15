package com.soon83.infrastructure.message;

import com.soon83.domain.message.Message;
import com.soon83.domain.message.MessageStore;
import com.soon83.domain.message.reply.MessageReply;
import com.soon83.infrastructure.message.reply.MessageReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageStoreImpl implements MessageStore {

    private final MessageRepository messageRepository;
    private final MessageReplyRepository messageReplyRepository;

    @Override
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public MessageReply create(MessageReply messageReply) {
        return messageReplyRepository.save(messageReply);
    }
}
