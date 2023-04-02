package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessageCommand;
import lombok.Builder;

@Builder
public record ReceiveMessageRemoveRequest(
        Long targetMemberId
) {
    public ReceiveMessageCommand.DeleteReceiveMessage toDeleteReceiveMessageCommand() {
        return ReceiveMessageCommand.DeleteReceiveMessage.builder()
                .targetMemberId(targetMemberId)
                .build();
    }
}
