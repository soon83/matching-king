package com.soon83.interfaces.receivemessage;

import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import com.soon83.interfaces.member.MemberResponse;
import com.soon83.interfaces.message.MessageResponse;
import com.soon83.interfaces.reply.MessageReplyResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ReceiveMessageResponse(
        Long receiveMessageId,
        boolean hiddenFromSender,
        boolean hiddenFromTargetMember,
        MemberResponse sender,
        Long targetMemberId,
        MessageResponse message,
        NotificationResponse messageNotification,
        List<MessageReplyResponse> messageReplies
) {
    public ReceiveMessageResponse(
            ReceiveMessageQuery query,
            List<MessageReplyResponse> messageReplies
    ) {
        this(
                query.id(),
                query.hiddenFromSender(),
                query.hiddenFromTargetMember(),
                new MemberResponse(query.sender()),
                query.targetMemberId(),
                new MessageResponse(query.message()),
                new NotificationResponse(query.messageNotification()),
                messageReplies
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
