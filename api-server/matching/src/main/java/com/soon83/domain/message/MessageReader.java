package com.soon83.domain.message;

public interface MessageReader {
    void checkMessageLimit(Long memberId, int sendMessageCount);
}
