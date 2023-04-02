package com.soon83.domain.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationDeleteCommand;
import com.soon83.domain.receivemessage.notification.NotificationUpdateToReadCommand;
import com.soon83.domain.receivemessage.reply.MessageReplyCreateCommand;

import java.util.List;

public interface ReceiveMessageService {
    List<ReceiveMessageNotificationQuery> searchNotificationsOfTargetMember(Long targetMemberId);

    List<ReceiveMessageQuery> searchReceiveMessagesOfTargetMember(Long targetMemberId);

    void removeReceiveMessage(Long receiveMessageId, ReceiveMessageDeleteCommand command);

    void changeToReadNotification(Long receiveMessageId, NotificationUpdateToReadCommand command);

    void removeReceiveMessageNotification(Long receiveMessageId, NotificationDeleteCommand command);

    Long registerMessageReply(Long receiveMessageId, MessageReplyCreateCommand command);
}
