package com.soon83.domain.message;

import com.soon83.domain.member.Member;
import lombok.Builder;

@Builder
public record MessageCreateCommand(
        String content,
        Long senderId
) {
    public Message toEntity(Member sender) {
        return Message.builder()
                .content(content)
                .sender(sender)
                .build();
    }
}
