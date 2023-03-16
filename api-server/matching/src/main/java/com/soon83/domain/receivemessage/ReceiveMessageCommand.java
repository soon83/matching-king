package com.soon83.domain.receivemessage;

import lombok.Builder;
import lombok.Data;

public class ReceiveMessageCommand {

    @Data
    public static class DeleteReceiveMessage {
        private final Long targetMemberId;

        @Builder
        public DeleteReceiveMessage(
                Long targetMemberId
        ) {
            this.targetMemberId = targetMemberId;
        }
    }
}
