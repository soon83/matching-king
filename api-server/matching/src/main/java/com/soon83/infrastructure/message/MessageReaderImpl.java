package com.soon83.infrastructure.message;

import com.soon83.domain.message.Message;
import com.soon83.domain.message.MessageReader;
import com.soon83.domain.message.reply.MessageReply;
import com.soon83.exception.message.MessageNotFoundException;
import com.soon83.exception.message.MessageReplyNotFoundException;
import com.soon83.exception.message.MessageTransferLimitExceededException;
import com.soon83.infrastructure.message.reply.MessageReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageReaderImpl implements MessageReader {

    private final MessageRepository messageRepository;
    private final MessageReplyRepository messageReplyRepository;

    @Override
    public Message readById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(MessageNotFoundException::new);
    }

    @Override
    public MessageReply findLatelyMessageReplyById(Long messageId) {
        return messageReplyRepository.findLatelyMessageReplyById(messageId)
                .orElseThrow(MessageReplyNotFoundException::new);
    }

    @Override
    public void checkMessageLimit(Long memberId, int sendMessageCount) {
        if (messageRepository.findLimitMessageByMemberId(memberId, sendMessageCount) == sendMessageCount) {
            throw new MessageTransferLimitExceededException();
        }
    }
}
