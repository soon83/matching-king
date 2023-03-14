package com.soon83.infrastructure.message;

public interface MessageRepositoryQuerydsl {
    int findLimitMessageByMemberId(Long memberId, int sendMessageCount);
}