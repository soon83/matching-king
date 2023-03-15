package com.soon83.interfaces.message;

import com.soon83.domain.message.MessageCommand;
import com.soon83.domain.message.MessageQuery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

public class MessageDto {

    @Data
    public static class RegisterRequest {
        @NotBlank(message = "필수값")
        @Size(max = 1000, message = "1000 자 이내")
        private String messageContent;
        @NotNull(message = "필수값")
        private Long messageSenderId;

        public MessageCommand.CreateMessage toCreateMessageCommand() {
            return MessageCommand.CreateMessage.builder()
                    .content(messageContent)
                    .senderId(messageSenderId)
                    .build();
        }
    }

    @Data
    public static class RegisterResponse {
        private final Long messageId;

        @Builder
        public RegisterResponse(Long messageId) {
            this.messageId = messageId;
        }
    }

    @Data
    public static class Main {
        private final Long messageId;
        private final String content;
        private final Long senderId;

        @Builder
        public Main(
                Long messageId,
                String content,
                Long senderId
        ) {
            this.messageId = messageId;
            this.content = content;
            this.senderId = senderId;
        }

        public Main(MessageQuery.Main query) {
            this.messageId = query.getId();
            this.content = query.getContent();
            this.senderId = query.getSenderId();
        }
    }
}
