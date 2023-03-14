package com.soon83.infrastructure.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessage;
import com.soon83.domain.receivemessage.ReceiveMessageReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveMessageReaderImpl implements ReceiveMessageReader {

    private final ReceiveMessageRepository receiveMessageRepository;

    @Override
    public List<ReceiveMessage> searchReceiveMessagesOfMember(Long targetMemberId) {
        return receiveMessageRepository.searchReceiveMessagesOfMember(targetMemberId);
    }
}
