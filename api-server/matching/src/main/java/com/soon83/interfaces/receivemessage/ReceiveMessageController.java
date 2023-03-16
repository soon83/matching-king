package com.soon83.interfaces.receivemessage;

import com.soon83.CommonResponse;
import com.soon83.application.NotificationApplication;
import com.soon83.application.ReceiveMessageApplication;
import com.soon83.domain.receivemessage.ReceiveMessageQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receive-messages")
public class ReceiveMessageController {

    private final NotificationApplication notificationApplication;
    private final ReceiveMessageApplication receiveMessageApplication;

    /**
     * [받은 쪽지 알림] 목록 조회
     * - 수신된 쪽지 알림
     */
    @GetMapping("/notifications")
    public CommonResponse<List<ReceiveMessageDto.NotificationResponse>> searchNotificationsOfTargetMember(@ModelAttribute @Valid ReceiveMessageDto.SearchCondition request) {
        log.debug("# searchNotificationsOfTargetMember # request: {}", request);
        List<ReceiveMessageQuery.Notification> notifications = receiveMessageApplication.searchNotificationsOfTargetMember(request.getTargetMemberId());
        List<ReceiveMessageDto.NotificationResponse> response = notifications.stream()
                .map(ReceiveMessageDto.NotificationResponse::new)
                .toList();
        return CommonResponse.success(response);
    }

    /**
     * [받은 쪽지 알림] 단건 수정
     * - 쪽지 읽음 / 읽음 표시
     */
    @PatchMapping("/{receiveMessageId}/notifications/{messageNotificationId}/hide")
    public CommonResponse<Void> changeToReadNotification(@PathVariable Long receiveMessageId, @PathVariable Long messageNotificationId, @RequestBody @Valid NotificationDto.ChangeReadRequest request) {
        var command = request.toUpdateNotificationCommand(messageNotificationId);
        notificationApplication.changeToReadNotification(receiveMessageId, command);
        return CommonResponse.success();
    }

    /**
     * [받은 쪽지 알림] 단건 삭제
     * - 쪽지 알림 삭제
     */
    @DeleteMapping("/{receiveMessageId}/notifications/{messageNotificationId}")
    public CommonResponse<Void> removeNotification(@PathVariable Long receiveMessageId, @PathVariable Long messageNotificationId, @RequestBody @Valid NotificationDto.RemoveRequest request) {
        var command = request.toDeleteNotificationCommand(messageNotificationId);
        notificationApplication.removeNotification(receiveMessageId, command);
        return CommonResponse.success();
    }

    /**
     * [받은 쪽지함] 목록 조회
     * - 내 쪽지함 보기
     */
    @GetMapping
    public CommonResponse<List<ReceiveMessageDto.Main>> searchReceiveMessagesOfTargetMember(@ModelAttribute @Valid ReceiveMessageDto.SearchCondition request) {
        log.debug("# searchReceiveMessagesOfTargetMember # request: {}", request);
        List<ReceiveMessageQuery.Main> receiveMessagesOfMember = receiveMessageApplication.searchReceiveMessagesOfTargetMember(request.getTargetMemberId());
        List<ReceiveMessageDto.Main> response = receiveMessagesOfMember.stream()
                .map(ReceiveMessageDto.Main::new)
                .toList();
        return CommonResponse.success(response);
    }

    /**
     * [받은 쪽지함] 단건 삭제
     * - 쪽지 삭제
     */
    @DeleteMapping("{receiveMessageId}")
    public CommonResponse<Void> removeReceiveMessage(@PathVariable Long receiveMessageId, @RequestBody @Valid ReceiveMessageDto.RemoveRequest request) {
        log.debug("# removeReceiveMessage # request: {}", request);
        var command = request.toDeleteReceiveMessageCommand();
        receiveMessageApplication.removeReceiveMessage(receiveMessageId, command);
        return CommonResponse.success();
    }
}
