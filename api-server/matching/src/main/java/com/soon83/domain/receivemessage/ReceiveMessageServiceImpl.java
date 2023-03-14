package com.soon83.domain.receivemessage;

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

    @Override
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
}
