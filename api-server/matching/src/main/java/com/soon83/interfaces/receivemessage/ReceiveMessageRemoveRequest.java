package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessageDeleteCommand;
import lombok.Builder;

@Builder
public record ReceiveMessageRemoveRequest(
        Long targetMemberId
) {
    public ReceiveMessageDeleteCommand toDeleteReceiveMessageCommand() {
        return ReceiveMessageDeleteCommand.builder()
                .targetMemberId(targetMemberId)
                .build();
    }
}
