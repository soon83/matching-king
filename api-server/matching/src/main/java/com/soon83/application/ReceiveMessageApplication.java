package com.soon83.application;

import com.soon83.domain.receivemessage.ReceiveMessageCommand;
import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import com.soon83.domain.receivemessage.ReceiveMessageService;
import com.soon83.domain.receivemessage.reply.MessageReplyCommand;
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

    public void removeReceiveMessage(Long receiveMessageId, ReceiveMessageCommand.DeleteReceiveMessage command) {
        receiveMessageService.removeReceiveMessage(receiveMessageId, command);
    }

    public Long registerMessageReply(Long receiveMessageId, MessageReplyCommand.CreateReply command) {
        return receiveMessageService.registerMessageReply(receiveMessageId, command);
    }
}
