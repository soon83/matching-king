package com.soon83.domain.receivemessage;

import lombok.Builder;
import lombok.Data;

public class ReceiveMessageQuery {

    @Data
    public static class Main {
        private final Long id;
        private boolean hiddenFromSender;
        private boolean hiddenFromTargetMember;
        private final Long targetMemberId;
        private final Long messageId;
        private final Long notificationId;

        @Builder
        public Main(
                Long id,
                boolean hiddenFromSender,
                boolean hiddenFromTargetMember,
                Long targetMemberId,
                Long messageId,
                Long notificationId
        ) {
            this.id = id;
            this.hiddenFromSender = hiddenFromSender;
            this.hiddenFromTargetMember = hiddenFromTargetMember;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
            this.notificationId = notificationId;
        }

        public Main(ReceiveMessage entity) {
            this.id = entity.getId();
            this.hiddenFromSender = entity.isHiddenFromSender();
            this.hiddenFromTargetMember = entity.isHiddenFromTargetMember();
            this.targetMemberId = entity.getTargetMember().getId();
            this.messageId = entity.getMessage().getId();
            this.notificationId = entity.getNotification().getId();
        }
    }
}
