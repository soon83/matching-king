package com.soon83.interfaces.message;

import com.soon83.domain.message.MessageCreateCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record MessageRegisterRequest(
        @NotBlank(message = "필수값")
        @Size(max = 1000, message = "1000 자 이내")
        String messageContent,
        @NotNull(message = "필수값")
        Long messageSenderId
) {
    public MessageCreateCommand toCreateMessageCommand() {
        return MessageCreateCommand.builder()
                .content(messageContent)
                .senderId(messageSenderId)
                .build();
    }
}
