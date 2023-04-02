package com.soon83.interfaces.reply;

import com.soon83.domain.receivemessage.reply.MessageReplyCreateCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record MessageReplyRegisterRequest(
        @NotBlank(message = "필수값")
        @Size(max = 1000, message = "1000 자 이내")
        String messageReplyContent,
        @NotNull(message = "필수값")
        Long replyMemberId
) {
    public MessageReplyCreateCommand toCreateMessageReplyCommand() {
        return MessageReplyCreateCommand.builder()
                .content(messageReplyContent)
                .replyMemberId(replyMemberId)
                .build();
    }
}
