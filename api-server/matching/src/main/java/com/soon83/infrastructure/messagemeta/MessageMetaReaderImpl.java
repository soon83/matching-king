package com.soon83.infrastructure.messagemeta;

import com.soon83.domain.messagemeta.MessageMeta;
import com.soon83.domain.messagemeta.MessageMetaReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageMetaReaderImpl implements MessageMetaReader {

    private final MessageMetaRepository messageMetaRepository;

    @Override
    public List<MessageMeta> searchMessageMetasOfMember(Long targetMemberId) {
        return messageMetaRepository.findByTargetMemberId(targetMemberId);
    }
}
