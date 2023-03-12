package com.soon83.domain.message;

import java.util.List;

public interface MessageService {
    Long registerMessage(MessageCommand.CreateMessage createMessageCommand);
    List<MessageQuery.Main> searchMessages(MessageQuery.SearchCondition condition);
}
