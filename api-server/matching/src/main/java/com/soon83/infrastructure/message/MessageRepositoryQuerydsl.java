package com.soon83.infrastructure.message;

import com.soon83.domain.message.Message;
import com.soon83.domain.message.MessageQuery;

import java.util.List;

public interface MessageRepositoryQuerydsl {
    List<Message> readAllBySearchCondition(MessageQuery.SearchCondition condition);
    int findLimitMessageByMemberId(Long memberId, int sendMessageCount);
}