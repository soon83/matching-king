package com.soon83.domain.receivemessage;

import com.soon83.domain.receivemessage.notification.NotificationDeleteCommand;
import com.soon83.domain.receivemessage.notification.NotificationUpdateToReadCommand;
import com.soon83.domain.receivemessage.reply.MessageReply;
import com.soon83.domain.receivemessage.reply.MessageReplyCreateCommand;
import com.soon83.domain.receivemessage.reply.MessageReplyQuery;
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
    public List<ReceiveMessageNotificationQuery> searchNotificationsOfTargetMember(Long targetMemberId) {
        return receiveMessageReader.searchNotificationsOfTargetMember(targetMemberId).stream()
                .map(ReceiveMessageNotificationQuery::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceiveMessageQuery> searchReceiveMessagesOfTargetMember(Long targetMemberId) {
        List<ReceiveMessage> receiveMessages = receiveMessageReader.searchReceiveMessagesOfTargetMember(targetMemberId);
        return receiveMessages.stream()
                .map(rm -> new ReceiveMessageQuery(rm, rm.getMessageReplies().stream()
                        .map(MessageReplyQuery::new)
                        .toList()))
                .toList();
    }

    @Override
    @Transactional
    public void removeReceiveMessage(Long receiveMessageId, ReceiveMessageDeleteCommand command) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        receiveMessage.validateTargetMemberIdEqual(command.targetMemberId());
        receiveMessage.deleteReceiveMessage();
    }

    @Override
    @Transactional
    public void changeToReadNotification(Long receiveMessageId, NotificationUpdateToReadCommand command) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        receiveMessage.validateReceiveMessageByTargetMember(command.targetMemberId(), command.messageNotificationId());
        receiveMessage.changeToReadNotification();
    }

    @Override
    @Transactional
    public void removeReceiveMessageNotification(Long receiveMessageId, NotificationDeleteCommand command) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        receiveMessage.validateReceiveMessageByTargetMember(command.targetMemberId(), command.messageNotificationId());
        receiveMessage.deleteNotification();
    }

    @Override
    @Transactional
    public Long registerMessageReply(Long receiveMessageId, MessageReplyCreateCommand command) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        receiveMessage.validateTargetMemberIdEqual(command.replyMemberId());
        MessageReply messageReply = receiveMessageReader.findLatelyMessageReplyByMessageId(receiveMessage.getMessage().getId());
        validateDoNotSeriesReply(command, receiveMessage, messageReply);
        MessageReply createdMessageReply = receiveMessageStore.create(command.toEntity(receiveMessage.getTargetMember(), receiveMessage));
        return createdMessageReply.getId();
    }

    private static void validateDoNotSeriesReply(MessageReplyCreateCommand command, ReceiveMessage receiveMessage, MessageReply messageReply) {
        if (messageReply == null) receiveMessage.validateMessageBy(command.replyMemberId());
        else messageReply.validateReplyMemberEqual(command.replyMemberId());
    }
}
