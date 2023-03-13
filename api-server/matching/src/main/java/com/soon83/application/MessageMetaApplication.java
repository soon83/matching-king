package com.soon83.application;

import com.soon83.domain.messagemeta.MessageMetaQuery;
import com.soon83.domain.messagemeta.MessageMetaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageMetaApplication {

    private final MessageMetaService messageMetaService;

    public List<MessageMetaQuery.Main> searchMessageNotificationsOfMember(Long targetMemberId) {
        return messageMetaService.searchMessageMetasOfMember(targetMemberId);
    }
}
