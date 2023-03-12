package com.soon83.application;

import com.soon83.domain.message.MessageCommand;
import com.soon83.domain.message.MessageQuery;
import com.soon83.domain.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageApplication {

    private final MessageService messageService;

    public Long registerMessage(MessageCommand.CreateMessage createMessageCommand) {
        return messageService.registerMessage(createMessageCommand);
    }

    public List<MessageQuery.Main> searchMessages(MessageQuery.SearchCondition condition) {
        return messageService.searchMessages(condition);
    }
}
