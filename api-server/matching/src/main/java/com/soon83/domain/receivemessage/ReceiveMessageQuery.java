package com.soon83.domain.receivemessage;

import com.soon83.domain.member.MemberQuery;
import com.soon83.domain.message.MessageQuery;
import com.soon83.domain.receivemessage.notification.NotificationQuery;
import com.soon83.domain.receivemessage.reply.MessageReplyQuery;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class ReceiveMessageQuery {

    @Data
    public static class Notification {
        private final Long id;
        private boolean hiddenFromSender;
        private boolean hiddenFromTargetMember;
        private final MemberQuery.Main sender;
        private final Long targetMemberId;
        private final Long messageId;
        private final NotificationQuery.Main messageNotification;

        @Builder
        public Notification(
                Long id,
                boolean hiddenFromSender,
                boolean hiddenFromTargetMember,
                MemberQuery.Main sender,
                Long targetMemberId,
                Long messageId,
                NotificationQuery.Main messageNotification
        ) {
            this.id = id;
            this.hiddenFromSender = hiddenFromSender;
            this.hiddenFromTargetMember = hiddenFromTargetMember;
            this.sender = sender;
            this.targetMemberId = targetMemberId;
            this.messageId = messageId;
            this.messageNotification = messageNotification;
        }

        public Notification(ReceiveMessage entity) {
            this.id = entity.getId();
            this.hiddenFromSender = entity.isHiddenFromSender();
            this.hiddenFromTargetMember = entity.isHiddenFromTargetMember();
            this.sender = new MemberQuery.Main(entity.getSender());
            this.targetMemberId = entity.getTargetMember().getId();
            this.messageId = entity.getMessage().getId();
            this.messageNotification = new NotificationQuery.Main(entity.getNotification());
        }
    }

    @Data
    public static class Main {
        private final Long id;
        private boolean hiddenFromSender;
        private boolean hiddenFromTargetMember;
        private final MemberQuery.Main sender;
        private final Long targetMemberId;
        private final MessageQuery.Main message;
        private final NotificationQuery.Main messageNotification;
        private final List<MessageReplyQuery.Main> messageReplies;

        @Builder
        public Main(
                Long id,
                boolean hiddenFromSender,
                boolean hiddenFromTargetMember,
                MemberQuery.Main sender,
                Long targetMemberId,
                MessageQuery.Main message,
                NotificationQuery.Main messageNotification,
                List<MessageReplyQuery.Main> messageReplies
        ) {
            this.id = id;
            this.hiddenFromSender = hiddenFromSender;
            this.hiddenFromTargetMember = hiddenFromTargetMember;
            this.sender = sender;
            this.targetMemberId = targetMemberId;
            this.message = message;
            this.messageNotification = messageNotification;
            this.messageReplies = messageReplies;
        }

        public Main(ReceiveMessage entity, List<MessageReplyQuery.Main> messageReplies) {
            this.id = entity.getId();
            this.hiddenFromSender = entity.isHiddenFromSender();
            this.hiddenFromTargetMember = entity.isHiddenFromTargetMember();
            this.sender = new MemberQuery.Main(entity.getSender());
            this.targetMemberId = entity.getTargetMember().getId();
            this.message = new MessageQuery.Main(entity.getMessage());
            this.messageNotification = new NotificationQuery.Main(entity.getNotification());
            this.messageReplies = messageReplies;
        }

        /*public Main(ReceiveMessage entity, List<MessageReply> messageReplies) {
            this.id = entity.getId();
            this.hiddenFromSender = entity.isHiddenFromSender();
            this.hiddenFromTargetMember = entity.isHiddenFromTargetMember();
            this.sender = new MemberQuery.Main(entity.getSender());
            this.targetMemberId = entity.getTargetMember().getId();
            this.message = new MessageQuery.Main(entity.getMessage());
            this.messageNotification = new NotificationQuery.Main(entity.getNotification());
            this.messageReplies = messageReplies.stream()
                    .map(MessageReplyQuery.Main::new)
                    .toList();
        }*/
    }
}
