package com.soon83.domain.message;

import java.util.List;

public interface MessageReader {
    List<Message> readAllBySearchCondition(MessageQuery.SearchCondition condition);
    void checkMessageLimit(Long memberId, int sendMessageCount);
}
