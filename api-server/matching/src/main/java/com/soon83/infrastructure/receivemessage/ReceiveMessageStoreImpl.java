package com.soon83.infrastructure.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessageStore;
import com.soon83.domain.receivemessage.reply.MessageReply;
import com.soon83.infrastructure.receivemessage.reply.MessageReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveMessageStoreImpl implements ReceiveMessageStore {

    private final MessageReplyRepository messageReplyRepository;

    @Override
    public MessageReply create(MessageReply messageReply) {
        return messageReplyRepository.save(messageReply);
    }
}
