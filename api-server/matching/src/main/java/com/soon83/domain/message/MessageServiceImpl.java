package com.soon83.domain.message;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberReader;
import com.soon83.domain.message.reply.MessageReply;
import com.soon83.domain.message.reply.MessageReplyCommand;
import com.soon83.domain.receivemessage.ReceiveMessage;
import com.soon83.domain.receivemessage.ReceiveMessageReader;
import com.soon83.exception.message.MessageReplySeriesException;
import com.soon83.exception.receivemessage.NotMyReceiveMessageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MemberReader memberReader;
    private final MessageReader messageReader;
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
    public Long registerMessageReply(MessageReplyCommand.CreateReply createMessageReplyCommand) {
        Member replyMember = memberReader.readById(createMessageReplyCommand.getReplyMemberId());
        Message message = messageReader.readById(createMessageReplyCommand.getMessageId());
        // TODO senderId == memberId OR targetMemberId == memberId 만 만족하여야 등록되도록 수정하자
        //MessageReply messageReply = messageReader.findLatelyMessageReplyById(createMessageReplyCommand.getMessageId());
        //checkSeriesReply(createMessageReplyCommand, messageReply);
        MessageReply createdMessageReply = messageStore.create(createMessageReplyCommand.toEntity(replyMember, message));
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

    private static void checkSeriesReply(MessageReplyCommand.CreateReply createMessageReplyCommand, MessageReply messageReply) {
        if (Objects.equals(messageReply.getReplyMember().getId(), createMessageReplyCommand.getReplyMemberId())) {
            throw new MessageReplySeriesException();
        }
    }
}
