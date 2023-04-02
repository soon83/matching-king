package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessageNotificationQuery;
import com.soon83.interfaces.member.MemberResponse;
import lombok.Builder;

@Builder
public record ReceiveMessageNotificationResponse(
        Long receiveMessageId,
        boolean hiddenFromSender,
        boolean hiddenFromTargetMember,
        MemberResponse sender,
        Long targetMemberId,
        Long messageId,
        NotificationResponse messageNotification
) {
    public ReceiveMessageNotificationResponse(ReceiveMessageNotificationQuery query) {
        this(
                query.id(),
                query.hiddenFromSender(),
                query.hiddenFromTargetMember(),
                new MemberResponse(query.sender()),
                query.targetMemberId(),
                query.messageId(),
                new NotificationResponse(query.messageNotification())
        );
    }

    public String getDescription() {
        return (this.sender.memberId() == this.targetMemberId) ? "보낸 쪽지" : "받은 쪽지";
    }

    public boolean isSend() {
        return (this.sender.memberId() == this.targetMemberId);
    }

    public boolean isReceived() {
        return !isSend();
    }
}
