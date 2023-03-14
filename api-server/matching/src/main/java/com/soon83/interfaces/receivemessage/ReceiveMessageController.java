package com.soon83.interfaces.receivemessage;

import com.soon83.CommonResponse;
import com.soon83.application.ReceiveMessageApplication;
import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members/{memberId}/receive-messages")
public class ReceiveMessageController {

    private final ReceiveMessageApplication receiveMessageApplication;

    /**
     * [쪽지함] 목록 조회
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
     * [알림] 목록 조회
     */
    @GetMapping("/notifications")
    public CommonResponse<List<ReceiveMessageDto.NotificationResponse>> searchNotificationsOfTargetMember(@PathVariable Long memberId) {
        log.debug("# searchNotificationsOfTargetMember # memberId: {}", memberId);
        List<ReceiveMessageQuery.Notification> notifications = receiveMessageApplication.searchNotificationsOfTargetMember(memberId);
        log.debug("### notifications: {} 건", notifications.size());
        List<ReceiveMessageDto.NotificationResponse> response = notifications.stream()
                .map(ReceiveMessageDto.NotificationResponse::new)
                .toList();
        return CommonResponse.success(response);
    }
}
