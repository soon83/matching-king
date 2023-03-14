package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import lombok.Builder;
import lombok.Data;

public class ReceiveMessageDto {

    @Data
    public static class Main {
        private final Long receiveMessageId;
        private boolean hiddenFromSender;
        private boolean hiddenFromTargetMember;
        private final Long targetMemberId;
        private final Long messageId;
        private final Long notificationId;

        @Builder
        public Main(
                Long receiveMessageId,
                boolean hiddenFromSender,
                boolean hiddenFromTargetMember,
                Long targetMemberId,
                Long messageId,
                Long notificationId
        ) {
            this.receiveMessageId = receiveMessageId;
            this.hiddenFromSender = hiddenFromSender;
            this.hiddenFromTargetMember = hiddenFromTargetMember;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
            this.notificationId = notificationId;
        }

        public Main(ReceiveMessageQuery.Main main) {
            this.receiveMessageId = main.getId();
            this.hiddenFromSender = main.isHiddenFromSender();
            this.hiddenFromTargetMember = main.isHiddenFromTargetMember();
            this.targetMemberId = main.getTargetMemberId();
            this.messageId = main.getMessageId();
            this.notificationId = main.getNotificationId();
        }
    }
}
