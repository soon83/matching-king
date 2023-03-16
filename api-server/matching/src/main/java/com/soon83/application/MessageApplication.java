package com.soon83.application;

import com.soon83.domain.message.MessageCommand;
import com.soon83.domain.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageApplication {

    private final MessageService messageService;

    public Long registerMessage(MessageCommand.CreateMessage command) {
        return messageService.registerMessage(command);
    }
}
