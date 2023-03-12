package com.soon83.domain.message;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberReader;
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
        Member member = memberReader.readMemberLimitById(createMessageCommand.getWriterMemberId());
        messageReader.checkMessageLimit(member.getId(), member.getLimit().getSendMessageCount());
        Message createdMessage = messageStore.create(createMessageCommand.toEntity(member));
        return createdMessage.getId();
    }

    @Override
    public List<MessageQuery.Main> searchMessages(MessageQuery.SearchCondition condition) {
        return messageReader.readAllBySearchCondition(condition).stream()
                .map(MessageQuery.Main::new)
                .toList();
    }
}
