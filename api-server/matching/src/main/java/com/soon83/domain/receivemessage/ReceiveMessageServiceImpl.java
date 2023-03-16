package com.soon83.domain.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationCommand;
import com.soon83.domain.receivemessage.reply.MessageReply;
import com.soon83.domain.receivemessage.reply.MessageReplyCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveMessageServiceImpl implements ReceiveMessageService {

    private final ReceiveMessageReader receiveMessageReader;
    private final ReceiveMessageStore receiveMessageStore;

    @Override
    @Transactional(readOnly = true)
    public List<ReceiveMessageQuery.Notification> searchNotificationsOfTargetMember(Long targetMemberId) {
        return receiveMessageReader.searchNotificationsOfTargetMember(targetMemberId).stream()
                .map(ReceiveMessageQuery.Notification::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceiveMessageQuery.Main> searchReceiveMessagesOfTargetMember(Long targetMemberId) {
        return receiveMessageReader.searchReceiveMessagesOfTargetMember(targetMemberId).stream()
                .map(ReceiveMessageQuery.Main::new)
                .toList();
    }

    @Override
    @Transactional
    public void removeReceiveMessage(Long receiveMessageId, ReceiveMessageCommand.DeleteReceiveMessage command) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        receiveMessage.validateTargetMemberIdEqual(command.getTargetMemberId());
        receiveMessage.deleteReceiveMessage();
    }

    @Override
    @Transactional
    public void changeToReadNotification(Long receiveMessageId, NotificationCommand.UpdateToRead command) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        receiveMessage.validateReceiveMessageByTargetMember(command.getTargetMemberId(), command.getMessageNotificationId());
        receiveMessage.changeToReadNotification();
    }

    @Override
    @Transactional
    public void removeReceiveMessageNotification(Long receiveMessageId, NotificationCommand.DeleteNotification command) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        receiveMessage.validateReceiveMessageByTargetMember(command.getTargetMemberId(), command.getMessageNotificationId());
        receiveMessage.deleteNotification();
    }

    @Override
    @Transactional
    public Long registerMessageReply(Long receiveMessageId, MessageReplyCommand.CreateReply command) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        receiveMessage.validateTargetMemberIdEqual(command.getReplyMemberId());
        MessageReply messageReply = receiveMessageReader.findLatelyMessageReplyByMessageId(receiveMessage.getMessage().getId());
        validateDoNotSeriesReply(command, receiveMessage, messageReply);
        MessageReply createdMessageReply = receiveMessageStore.create(command.toEntity(receiveMessage.getTargetMember(), receiveMessage));
        return createdMessageReply.getId();
    }

    private static void validateDoNotSeriesReply(MessageReplyCommand.CreateReply command, ReceiveMessage receiveMessage, MessageReply messageReply) {
        if (messageReply == null) receiveMessage.validateMessageBy(command.getReplyMemberId());
        else messageReply.validateReplyMemberEqual(command.getReplyMemberId());
    }
}
