package com.soon83.domain.receivemessage.reply;

import com.soon83.domain.member.Member;
import com.soon83.domain.message.Message;
import com.soon83.domain.receivemessage.ReceiveMessage;
import lombok.Builder;

@Builder
public record MessageReplyCreateCommand(
        String content,
        Long replyMemberId
) {
    public MessageReply toEntity(
            Member replyMember,
            ReceiveMessage receiveMessage,
            Message message
    ) {
        return MessageReply.builder()
                .content(content)
                .replyMember(replyMember)
                .receiveMessage(receiveMessage)
                .message(message)
                .build();
    }
}
