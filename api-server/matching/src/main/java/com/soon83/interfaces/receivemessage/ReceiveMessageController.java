package com.soon83.interfaces.receivemessage;

import com.soon83.CommonResponse;
import com.soon83.application.ReceiveMessageApplication;
import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members/{memberId}/receive-messages")
public class ReceiveMessageController {

    private final ReceiveMessageApplication receiveMessageApplication;

    /**
     * [쪽지함] 메시지 목록 조회
     */
    @GetMapping
    public CommonResponse<List<ReceiveMessageDto.Main>> searchReceiveMessagesOfTargetMember(@PathVariable Long memberId) {
        log.debug("# searchReceiveMessagesOfTargetMember # memberId: {}", memberId);
        List<ReceiveMessageQuery.Main> receiveMessagesOfMember = receiveMessageApplication.searchReceiveMessagesOfTargetMember(memberId);
        List<ReceiveMessageDto.Main> response = receiveMessagesOfMember.stream()
                .map(ReceiveMessageDto.Main::new)
                .toList();
        return CommonResponse.success(response);
    }

    /**
     * [쪽지함] 단건 삭제
     */
    @DeleteMapping("{receiveMessageId}")
    public CommonResponse<Void> removeReceiveMessage(@PathVariable Long memberId, @PathVariable Long receiveMessageId) {
        log.debug("# searchReceiveMessagesOfTargetMember # memberId: {}, receiveMessageId: {}", memberId, receiveMessageId);
        receiveMessageApplication.removeReceiveMessage(memberId, receiveMessageId);
        return CommonResponse.success();
    }

    /**
     * [쪽지함] 알림 목록 조회
     */
    @GetMapping("/notifications")
    public CommonResponse<List<ReceiveMessageDto.NotificationResponse>> searchNotificationsOfTargetMember(@PathVariable Long memberId) {
        log.debug("# searchNotificationsOfTargetMember # memberId: {}", memberId);
        List<ReceiveMessageQuery.Notification> notifications = receiveMessageApplication.searchNotificationsOfTargetMember(memberId);
        List<ReceiveMessageDto.NotificationResponse> response = notifications.stream()
                .map(ReceiveMessageDto.NotificationResponse::new)
                .toList();
        return CommonResponse.success(response);
    }
}
