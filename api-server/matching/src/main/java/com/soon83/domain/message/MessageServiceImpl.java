package com.soon83.domain.message;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberReader;
import com.soon83.domain.receivemessage.ReceiveMessage;
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
    private final MessageStore messageStore;

    @Override
    @Transactional
    public Long registerMessage(MessageCommand.CreateMessage createMessageCommand) {
        Member member = memberReader.readMemberMatchingConditionAndLimitById(createMessageCommand.getSenderId());
        messageReader.checkMessageLimit(member.getId(), member.getLimit().getSendMessageCount());
        Message createdMessage = messageStore.create(createMessageCommand.toEntity(member));
        List<Member> matchingMembers = memberReader.readLimitMembersByMatchingCondition(member.getMatchingCondition(), member.getLimit().getSendMessageNotificationCount());
        createReceiveMessages(member, createdMessage, matchingMembers);
        return createdMessage.getId();
    }

    private static void createReceiveMessages(Member member, Message createdMessage, List<Member> members) {
        members.forEach(targetMember -> ReceiveMessage.builder()
                .targetMember(targetMember)
                .message(createdMessage)
                .build());
        ReceiveMessage.builder()
                .targetMember(member)
                .message(createdMessage)
                .build();
    }
}
