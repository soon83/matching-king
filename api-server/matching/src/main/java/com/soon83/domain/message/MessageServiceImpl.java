package com.soon83.domain.message;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberReader;
import com.soon83.domain.messagemeta.MessageMeta;
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
        List<Member> members = memberReader.readLimitMembersByMatchingCondition(member.getMatchingCondition(), member.getLimit().getSendMessageNotificationCount());
        members.forEach(targetMember -> MessageMeta.builder()
                        .targetMember(targetMember)
                        .message(createdMessage)
                        .build());
        return createdMessage.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageQuery.Main> searchMessages(MessageQuery.SearchCondition condition) {
        return messageReader.readAllBySearchCondition(condition).stream()
                .map(MessageQuery.Main::new)
                .toList();
    }
}
