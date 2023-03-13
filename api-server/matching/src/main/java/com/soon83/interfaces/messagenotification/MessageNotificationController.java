package com.soon83.interfaces.messagenotification;

import com.soon83.CommonResponse;
import com.soon83.application.MessageNotificationApplication;
import com.soon83.domain.notification.MessageNotificationQuery;
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
@RequestMapping("/api/v1/members/{memberId}/message-notifications")
public class MessageNotificationController {

    private final MessageNotificationApplication messageNotificationApplication;

    /**
     * [메시지 알림] 목록 조회
     */
    @GetMapping
    public CommonResponse<List<MessageNotificationDto.Main>> searchMessageNotificationsOfMember(@PathVariable Long memberId) {
        log.debug("# searchMessageNotificationsOfMember # memberId: {}", memberId);
        List<MessageNotificationQuery.Main> messageNotificationsOfMember = messageNotificationApplication.searchMessageNotificationsOfMember(memberId);
        List<MessageNotificationDto.Main> response = messageNotificationsOfMember.stream()
                .map(MessageNotificationDto.Main::new)
                .toList();
        return CommonResponse.success(response);
    }
}
