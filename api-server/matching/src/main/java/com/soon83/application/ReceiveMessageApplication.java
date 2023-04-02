package com.soon83.application;

import com.soon83.domain.receivemessage.ReceiveMessageDeleteCommand;
import com.soon83.domain.receivemessage.ReceiveMessageNotificationQuery;
import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import com.soon83.domain.receivemessage.ReceiveMessageService;
import com.soon83.domain.receivemessage.reply.MessageReplyCreateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveMessageApplication {
    private final ReceiveMessageService receiveMessageService;

    public List<ReceiveMessageNotificationQuery> searchNotificationsOfTargetMember(Long targetMemberId) {
        return receiveMessageService.searchNotificationsOfTargetMember(targetMemberId);
    }

    public List<ReceiveMessageQuery> searchReceiveMessagesOfTargetMember(Long targetMemberId) {
        return receiveMessageService.searchReceiveMessagesOfTargetMember(targetMemberId);
    }

    public void removeReceiveMessage(Long receiveMessageId, ReceiveMessageDeleteCommand command) {
        receiveMessageService.removeReceiveMessage(receiveMessageId, command);
    }

    public Long registerMessageReply(Long receiveMessageId, MessageReplyCreateCommand command) {
        return receiveMessageService.registerMessageReply(receiveMessageId, command);
    }
}
