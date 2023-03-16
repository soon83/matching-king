package com.soon83.interfaces.reply;

import com.soon83.domain.receivemessage.reply.MessageReplyCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

public class MessageReplyDto {

    @Data
    public static class RegisterRequest {
        @NotBlank(message = "필수값")
        @Size(max = 1000, message = "1000 자 이내")
        private String messageReplyContent;
        @NotNull(message = "필수값")
        private Long replyMemberId;

        public MessageReplyCommand.CreateReply toCreateMessageReplyCommand() {
            return MessageReplyCommand.CreateReply.builder()
                    .content(messageReplyContent)
                    .replyMemberId(replyMemberId)
                    .build();
        }
    }

    @Data
    public static class RegisterResponse {
        private final Long messageReplyId;

        @Builder
        public RegisterResponse(Long messageReplyId) {
            this.messageReplyId = messageReplyId;
        }
    }

    /*@Data
    public static class Main {
        private final Long messageReplyId;
    }*/
}
