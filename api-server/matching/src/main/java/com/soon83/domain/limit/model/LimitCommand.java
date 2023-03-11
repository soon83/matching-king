package com.soon83.domain.limit.model;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;

public class LimitCommand {

    @Data
    public static class CreateLimit {
        private Member.Type memberType;
        private int sendMessageCount;

        @Builder
        public CreateLimit(
                Member.Type memberType,
                int sendMessageCount
        ) {
            this.memberType = memberType;
            this.sendMessageCount = sendMessageCount;
        }

        public Limit toEntity() {
            return Limit.builder()
                    .memberType(memberType)
                    .sendMessageCount(sendMessageCount)
                    .build();
        }
    }
}
