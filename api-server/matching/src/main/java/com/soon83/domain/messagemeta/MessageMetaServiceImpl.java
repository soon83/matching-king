package com.soon83.domain.messagemeta;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageMetaServiceImpl implements MessageMetaService {

    private final MessageMetaReader messageMetaReader;

    @Override
    @Transactional(readOnly = true)
    public List<MessageMetaQuery.Main> searchMessageMetasOfMember(Long targetMemberId) {
        return messageMetaReader.searchMessageMetasOfMember(targetMemberId).stream()
                .map(MessageMetaQuery.Main::new)
                .toList();
    }
}
