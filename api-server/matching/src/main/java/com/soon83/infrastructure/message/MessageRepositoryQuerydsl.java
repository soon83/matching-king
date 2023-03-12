package com.soon83.infrastructure.message;

import com.soon83.domain.message.Message;
import com.soon83.domain.message.MessageQuery;

import java.util.List;

public interface MessageRepositoryQuerydsl {
    int findLimitMessageByMemberId(Long memberId, int sendMessageCount);
    List<Message> readAllBySearchCondition(MessageQuery.SearchCondition condition);
}