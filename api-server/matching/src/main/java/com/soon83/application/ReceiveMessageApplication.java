package com.soon83.application;

import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import com.soon83.domain.receivemessage.ReceiveMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveMessageApplication {

    private final ReceiveMessageService receiveMessageService;

    public List<ReceiveMessageQuery.Notification> searchNotificationsOfTargetMember(Long targetMemberId) {
        return receiveMessageService.searchNotificationsOfTargetMember(targetMemberId);
    }

    public List<ReceiveMessageQuery.Main> searchReceiveMessagesOfTargetMember(Long targetMemberId) {
        return receiveMessageService.searchReceiveMessagesOfTargetMember(targetMemberId);
    }
}
