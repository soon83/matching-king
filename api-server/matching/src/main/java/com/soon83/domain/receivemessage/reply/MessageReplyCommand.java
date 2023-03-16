package com.soon83.domain.receivemessage.reply;

import com.soon83.domain.member.Member;
import com.soon83.domain.receivemessage.ReceiveMessage;
import lombok.Builder;
import lombok.Data;

public class MessageReplyCommand {

    @Data
    public static class CreateReply {
        private String content;
        private Long replyMemberId;

        @Builder
        public CreateReply(
                String content,
                Long replyMemberId
        ) {
            this.content = content;
            this.replyMemberId = replyMemberId;
        }

        public MessageReply toEntity(
                Member replyMember,
                ReceiveMessage receiveMessage
        ) {
            return MessageReply.builder()
                    .content(content)
                    .replyMember(replyMember)
                    .receiveMessage(receiveMessage)
                    .build();
        }
    }
}
