package com.soon83.domain.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationCommand;
import com.soon83.domain.receivemessage.reply.MessageReplyCommand;

import java.util.List;

public interface ReceiveMessageService {
    List<ReceiveMessageQuery.Notification> searchNotificationsOfTargetMember(Long targetMemberId);
    List<ReceiveMessageQuery.Main> searchReceiveMessagesOfTargetMember(Long targetMemberId);
    void removeReceiveMessage(Long receiveMessageId, ReceiveMessageCommand.DeleteReceiveMessage command);
    void changeToReadNotification(Long receiveMessageId, NotificationCommand.UpdateToRead command);
    void removeReceiveMessageNotification(Long receiveMessageId, NotificationCommand.DeleteNotification command);
    Long registerMessageReply(Long receiveMessageId, MessageReplyCommand.CreateReply command);
}
