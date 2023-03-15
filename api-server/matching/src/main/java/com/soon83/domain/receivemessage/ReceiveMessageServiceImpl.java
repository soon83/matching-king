package com.soon83.domain.receivemessage;

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
public class ReceiveMessageServiceImpl implements ReceiveMessageService {

    private final ReceiveMessageReader receiveMessageReader;

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
    public void removeReceiveMessage(Long memberId, Long receiveMessageId) {
        ReceiveMessage receiveMessage = receiveMessageReader.readById(receiveMessageId);
        checkMyReceiveMessage(memberId, receiveMessage);
        receiveMessage.removeReceiveMessage();
    }

    private static void checkMyReceiveMessage(Long memberId, ReceiveMessage receiveMessage) {
        if (!Objects.equals(receiveMessage.getTargetMember().getId(), memberId)) {
            throw new NotMyReceiveMessageException();
        }
    }
}
