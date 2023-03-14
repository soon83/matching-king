package com.soon83.infrastructure.message;

import com.soon83.domain.message.MessageReader;
import com.soon83.exception.message.MessageTransferLimitExceededException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageReaderImpl implements MessageReader {

    private final MessageRepository messageRepository;

    @Override
    public void checkMessageLimit(Long memberId, int sendMessageCount) {
        if (messageRepository.findLimitMessageByMemberId(memberId, sendMessageCount) == sendMessageCount) {
            throw new MessageTransferLimitExceededException();
        }
    }
}
