package com.soon83.domain.receivemessage.reply;

import lombok.Builder;
import lombok.Data;

public class MessageReplyQuery {

    @Data
    public static class Main {
        private final Long id;
        private final String content;
        private final Long replyMemberId;

        @Builder
        public Main(
                Long id,
                String content,
                Long replyMemberId
        ) {
            this.id = id;
            this.content = content;
            this.replyMemberId = replyMemberId;
        }

        public Main(MessageReply entity) {
            this.id = entity.getId();
            this.content = entity.getContent();
            this.replyMemberId = entity.getReplyMember().getId();
        }
    }
}
