package com.soon83.infrastructure.message;

import com.soon83.domain.message.Message;
import com.soon83.domain.message.MessageQuery;
import com.soon83.domain.message.MessageReader;
import com.soon83.exception.message.MessageTransferLimitExceededException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageReaderImpl implements MessageReader {

    private final MessageRepository messageRepository;

    @Override
    public List<Message> readAllBySearchCondition(MessageQuery.SearchCondition condition) {
        // TODO queryDsl 로 조회하도록 변경, 페이징도 하자
        return messageRepository.readAllBySearchCondition(condition);
    }

    @Override
    public void checkMessageLimit(Long memberId, int sendMessageCount) {
        if (messageRepository.findLimitMessageByMemberId(memberId, sendMessageCount) == sendMessageCount) {
            throw new MessageTransferLimitExceededException();
        }
    }
}
