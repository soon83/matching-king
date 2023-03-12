package com.soon83.domain.message;

import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;

public class MessageCommand {

    @Data
    public static class CreateMessage {
        private String content;
        private Long senderId;

        @Builder
        public CreateMessage(
                String content,
                Long senderId
        ) {
            this.content = content;
            this.senderId = senderId;
        }

        public Message toEntity(Member sender) {
            return Message.builder()
                    .content(content)
                    .sender(sender)
                    .build();
        }
    }
}
