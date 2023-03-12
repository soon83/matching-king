package com.soon83.domain.message;

import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;

public class MessageCommand {

    @Data
    public static class CreateMessage {
        private String content;
        private Long writerMemberId;

        @Builder
        public CreateMessage(
                String content,
                Long writerMemberId
        ) {
            this.content = content;
            this.writerMemberId = writerMemberId;
        }

        public Message toEntity(Member writerMember) {
            return Message.builder()
                    .content(content)
                    .writerMember(writerMember)
                    .build();
        }
    }
}
