package com.soon83.infrastructure.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessage;
import com.soon83.domain.receivemessage.ReceiveMessageReader;
import com.soon83.domain.receivemessage.reply.MessageReply;
import com.soon83.exception.receivemessage.ReceiveMessageNotFoundException;
import com.soon83.infrastructure.receivemessage.reply.MessageReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveMessageReaderImpl implements ReceiveMessageReader {

    private final ReceiveMessageRepository receiveMessageRepository;
    private final MessageReplyRepository messageReplyRepository;

    @Override
    public ReceiveMessage readById(Long receiveMessageId) {
        return receiveMessageRepository.findById(receiveMessageId)
                .orElseThrow(ReceiveMessageNotFoundException::new);
    }

    @Override
    public List<ReceiveMessage> searchReceiveMessagesOfTargetMember(Long targetMemberId) {
        return receiveMessageRepository.searchReceiveMessagesOfTargetMember(targetMemberId);
    }

    @Override
    public List<ReceiveMessage> searchNotificationsOfTargetMember(Long targetMemberId) {
        return receiveMessageRepository.searchNotificationsOfTargetMember(targetMemberId);
    }

    @Override
    public MessageReply findLatelyMessageReplyByMessageId(Long messageId) {
        return messageReplyRepository.findLatelyMessageReplyByMessageId(messageId)
                .orElse(null);
    }
}
