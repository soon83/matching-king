package com.soon83.domain.limit;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;

public class LimitCommand {

    @Data
    public static class CreateLimit {
        private Member.Type memberType;
        private int sendMessageCount;
        private int sendMessageNotificationCount;

        @Builder
        public CreateLimit(
                Member.Type memberType,
                int sendMessageCount,
                int sendMessageNotificationCount
        ) {
            this.memberType = memberType;
            this.sendMessageCount = sendMessageCount;
            this.sendMessageNotificationCount = sendMessageNotificationCount;
        }

        public Limit toEntity() {
            return Limit.builder()
                    .memberType(memberType)
                    .sendMessageCount(sendMessageCount)
                    .sendMessageNotificationCount(sendMessageNotificationCount)
                    .build();
        }
    }
}
