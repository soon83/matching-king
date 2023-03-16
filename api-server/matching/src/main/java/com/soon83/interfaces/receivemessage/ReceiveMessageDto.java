package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessageCommand;
import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import com.soon83.interfaces.member.MemberDto;
import com.soon83.interfaces.message.MessageDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

public class ReceiveMessageDto {

    @Data
    public static class SearchCondition {
        @NotNull(message = "필수값")
        private Long targetMemberId;
    }

    @Data
    public static class RemoveRequest {
        @NotNull(message = "필수값")
        private Long targetMemberId;

        public ReceiveMessageCommand.DeleteReceiveMessage toDeleteReceiveMessageCommand() {
            return ReceiveMessageCommand.DeleteReceiveMessage.builder()
                    .targetMemberId(targetMemberId)
                    .build();
        }
    }

    @Data
    public static class NotificationResponse {
        private final Long receiveMessageId;
        private boolean hiddenFromSender;
        private boolean hiddenFromTargetMember;
        private final MemberDto.Main sender;
        private final Long targetMemberId;
        private final Long messageId;
        private final NotificationDto.Main messageNotification;

        @Builder
        public NotificationResponse(
                Long receiveMessageId,
                boolean hiddenFromSender,
                boolean hiddenFromTargetMember,
                MemberDto.Main sender,
                Long targetMemberId,
                Long messageId,
                NotificationDto.Main messageNotification
        ) {
            this.receiveMessageId = receiveMessageId;
            this.hiddenFromSender = hiddenFromSender;
            this.hiddenFromTargetMember = hiddenFromTargetMember;
            this.sender = sender;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
            this.messageNotification = messageNotification;
        }

        public NotificationResponse(ReceiveMessageQuery.Notification query) {
            this.receiveMessageId = query.getId();
            this.hiddenFromSender = query.isHiddenFromSender();
            this.hiddenFromTargetMember = query.isHiddenFromTargetMember();
            this.sender = new MemberDto.Main(query.getSender());
            this.targetMemberId = query.getTargetMemberId();
            this.messageId = query.getMessageId();
            this.messageNotification = new NotificationDto.Main(query.getMessageNotification());
        }

        public String getDescription() {
            return (this.sender.getMemberId() == this.targetMemberId) ? "보낸 쪽지" : "받은 쪽지";
        }

        public boolean isSend() {
            return (this.sender.getMemberId() == this.targetMemberId);
        }

        public boolean isReceived() {
            return !isSend();
        }
    }

    @Data
    public static class Main {
        private final Long receiveMessageId;
        private boolean hiddenFromSender;
        private boolean hiddenFromTargetMember;
        private final MemberDto.Main sender;
        private final Long targetMemberId;
        private final MessageDto.Main message;
        private final NotificationDto.Main messageNotification;

        @Builder
        public Main(
                Long receiveMessageId,
                boolean hiddenFromSender,
                boolean hiddenFromTargetMember,
                MemberDto.Main sender,
                Long targetMemberId,
                MessageDto.Main message,
                NotificationDto.Main messageNotification
        ) {
            this.receiveMessageId = receiveMessageId;
            this.hiddenFromSender = hiddenFromSender;
            this.hiddenFromTargetMember = hiddenFromTargetMember;
            this.sender = sender;
            this.targetMemberId = targetMemberId;
            this.message = message;
            this.messageNotification = messageNotification;
        }

        public Main(ReceiveMessageQuery.Main query) {
            this.receiveMessageId = query.getId();
            this.hiddenFromSender = query.isHiddenFromSender();
            this.hiddenFromTargetMember = query.isHiddenFromTargetMember();
            this.sender = new MemberDto.Main(query.getSender());
            this.targetMemberId = query.getTargetMemberId();
            this.message = new MessageDto.Main(query.getMessage());
            this.messageNotification = new NotificationDto.Main(query.getMessageNotification());
        }

        public String getDescription() {
            return (this.sender.getMemberId() == this.targetMemberId) ? "보낸 쪽지" : "받은 쪽지";
        }

        public boolean isSend() {
            return (this.sender.getMemberId() == this.targetMemberId);
        }

        public boolean isReceived() {
            return !isSend();
        }
    }
}
