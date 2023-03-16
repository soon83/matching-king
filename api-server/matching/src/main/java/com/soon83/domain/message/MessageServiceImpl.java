package com.soon83.domain.message;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberReader;
import com.soon83.domain.message.reply.MessageReply;
import com.soon83.domain.message.reply.MessageReplyCommand;
import com.soon83.domain.receivemessage.ReceiveMessage;
import com.soon83.domain.receivemessage.ReceiveMessageReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MemberReader memberReader;
    private final MessageReader messageReader;
    private final ReceiveMessageReader receiveMessageReader;
    private final MessageStore messageStore;

    @Override
    @Transactional
    public Long registerMessage(MessageCommand.CreateMessage createMessageCommand) {
        Member sender = memberReader.readMemberMatchingConditionAndLimitById(createMessageCommand.getSenderId());
        messageReader.checkMessageLimit(sender.getId(), sender.getLimit().getSendMessageCount());
        Message createdMessage = messageStore.create(createMessageCommand.toEntity(sender));
        List<Member> targetMembers = memberReader.readLimitMembersByMatchingCondition(sender.getId(), sender.getMatchingCondition(), sender.getLimit().getSendMessageNotificationCount());
        createReceiveMessages(sender, createdMessage, targetMembers);
        return createdMessage.getId();
    }

    @Override
    @Transactional
    public Long registerMessageReply(Long receiveMessageId, MessageReplyCommand.CreateReply createMessageReplyCommand) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        receiveMessage.validateTargetMemberIdEqual(createMessageReplyCommand.getReplyMemberId());
        receiveMessage.validateMessageIdEqual(createMessageReplyCommand.getMessageId());
        MessageReply messageReply = messageReader.findLatelyMessageReplyById(createMessageReplyCommand.getMessageId());
        if (messageReply == null) {
            receiveMessage.getMessage().validateSenderEqual(createMessageReplyCommand.getReplyMemberId());
        } else {
            messageReply.validateReplyMemberEqual(createMessageReplyCommand.getReplyMemberId());
        }
        MessageReply createdMessageReply = messageStore.create(createMessageReplyCommand.toEntity(receiveMessage.getTargetMember(), receiveMessage.getMessage()));
        return createdMessageReply.getId();
    }

    private static void createReceiveMessages(Member sender, Message createdMessage, List<Member> targetMembers) {
        targetMembers.forEach(targetMember -> ReceiveMessage.builder()
                .sender(sender)
                .targetMember(targetMember)
                .message(createdMessage)
                .build());
        ReceiveMessage.builder()
                .sender(sender)
                .targetMember(sender)
                .message(createdMessage)
                .build();
    }
}
