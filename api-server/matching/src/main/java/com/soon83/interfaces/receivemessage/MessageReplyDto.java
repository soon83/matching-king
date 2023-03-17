package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.reply.MessageReplyQuery;
import lombok.Builder;
import lombok.Data;

public class MessageReplyDto {

    @Data
    public static class Main {
        private final Long messageReplyId;
        private final String content;
        private final Long replyMemberId;

        @Builder
        public Main(
                Long messageReplyId,
                String content,
                Long replyMemberId
        ) {
            this.messageReplyId = messageReplyId;
            this.content = content;
            this.replyMemberId = replyMemberId;
        }

        public Main(MessageReplyQuery.Main query) {
            this.messageReplyId = query.getId();
            this.content = query.getContent();
            this.replyMemberId = query.getReplyMemberId();
        }

    }
}
