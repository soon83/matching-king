package com.soon83.domain.receivemessage;

import com.soon83.domain.member.MemberQuery;
import com.soon83.domain.message.MessageQuery;
import com.soon83.domain.receivemessage.notification.NotificationQuery;
import com.soon83.domain.receivemessage.reply.MessageReplyQuery;
import lombok.Builder;

import java.util.List;

@Builder
public record ReceiveMessageQuery(
        Long id,
        boolean hiddenFromSender,
        boolean hiddenFromTargetMember,
        MemberQuery sender,
        Long targetMemberId,
        MessageQuery message,
        NotificationQuery messageNotification,
        List<MessageReplyQuery> messageReplies
) {
    public ReceiveMessageQuery(
            ReceiveMessage entity,
            List<MessageReplyQuery> messageReplies
    ) {
        this(
                entity.getId(),
                entity.isHiddenFromSender(),
                entity.isHiddenFromTargetMember(),
                new MemberQuery(entity.getSender()),
                entity.getTargetMember().getId(),
                new MessageQuery(entity.getMessage()),
                new NotificationQuery(entity.getNotification()),
                messageReplies
        );
    }
}
