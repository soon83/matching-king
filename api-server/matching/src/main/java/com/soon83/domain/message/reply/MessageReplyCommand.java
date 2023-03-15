package com.soon83.domain.message.reply;

import com.soon83.domain.member.Member;
import com.soon83.domain.message.Message;
import lombok.Builder;
import lombok.Data;

public class MessageReplyCommand {

    @Data
    public static class CreateReply {
        private String content;
        private Long replyMemberId;
        private Long messageId;

        @Builder
        public CreateReply(
                String content,
                Long replyMemberId,
                Long messageId
        ) {
            this.content = content;
            this.replyMemberId = replyMemberId;
            this.messageId = messageId;
        }

        public MessageReply toEntity(Member replyMember, Message message) {
            return MessageReply.builder()
                    .content(content)
                    .replyMember(replyMember)
                    .message(message)
                    .build();
        }
    }
}
