package com.soon83.interfaces.receivemessage;

import com.soon83.CommonResponse;
import com.soon83.application.NotificationApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members/{memberId}/receive-messages/{receiveMessageId}/notifications")
public class NotificationController {

    private final NotificationApplication notificationApplication;

    @PatchMapping("{messageNotificationId}/change-to-read")
    public CommonResponse<Void> changeToRead(@PathVariable Long memberId, @PathVariable Long receiveMessageId, @PathVariable Long messageNotificationId) {
        notificationApplication.changeToRead(memberId, receiveMessageId, messageNotificationId);
        return CommonResponse.success();
    }

    @DeleteMapping("{messageNotificationId}")
    public CommonResponse<Void> removeNotification(@PathVariable Long memberId, @PathVariable Long receiveMessageId, @PathVariable Long messageNotificationId) {
        notificationApplication.removeNotification(memberId, receiveMessageId, messageNotificationId);
        return CommonResponse.success();
    }
}
