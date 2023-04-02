package com.soon83.domain.receivemessage;

import com.soon83.domain.member.MemberQuery;
import com.soon83.domain.receivemessage.notification.NotificationQuery;
import lombok.Builder;

@Builder
public record ReceiveMessageNotificationQuery(
        Long id,
        boolean hiddenFromSender,
        boolean hiddenFromTargetMember,
        MemberQuery sender,
        Long targetMemberId,
        Long messageId,
        NotificationQuery messageNotification
) {
    public ReceiveMessageNotificationQuery(ReceiveMessage entity) {
        this(
                entity.getId(),
                entity.isHiddenFromSender(),
                entity.isHiddenFromTargetMember(),
                new MemberQuery(entity.getSender()),
                entity.getTargetMember().getId(),
                entity.getMessage().getId(),
                new NotificationQuery(entity.getNotification())
        );
    }
}
