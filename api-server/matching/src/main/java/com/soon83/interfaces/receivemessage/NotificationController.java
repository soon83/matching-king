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
@RequestMapping("/api/v1/members/{memberId}/notifications")
public class NotificationController {

    private final ReceiveMessageApplication receiveMessageApplication;

    /**
     * [메시지 함] 목록 조회
     */
    @GetMapping
    public CommonResponse<List<ReceiveMessageDto.NotificationResponse>> searchReceiveMessagesNotificationsOfTargetMember(@PathVariable Long memberId) {
        log.debug("# searchReceiveMessagesNotificationOfTargetMember # memberId: {}", memberId);
        List<ReceiveMessageQuery.Notification> receiveMessagesNotificationsOfMember = receiveMessageApplication.searchReceiveMessagesNotificationsOfTargetMember(memberId);
        List<ReceiveMessageDto.NotificationResponse> response = receiveMessagesNotificationsOfMember.stream()
                .map(ReceiveMessageDto.NotificationResponse::new)
                .toList();
        return CommonResponse.success(response);
    }
}
