package com.soon83.interfaces.messagemeta;

import com.soon83.CommonResponse;
import com.soon83.application.MessageMetaApplication;
import com.soon83.domain.messagemeta.MessageMetaQuery;
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
@RequestMapping("/api/v1/members/{memberId}/message-metas")
public class MessageMetaController {

    private final MessageMetaApplication messageMetaApplication;

    /**
     * [메시지 알림] 목록 조회
     */
    @GetMapping
    public CommonResponse<List<MessageMetaDto.Main>> searchMessageNotificationsOfMember(@PathVariable Long memberId) {
        log.debug("# searchMessageNotificationsOfMember # memberId: {}", memberId);
        List<MessageMetaQuery.Main> messageNotificationsOfMember = messageMetaApplication.searchMessageNotificationsOfMember(memberId);
        List<MessageMetaDto.Main> response = messageNotificationsOfMember.stream()
                .map(MessageMetaDto.Main::new)
                .toList();
        return CommonResponse.success(response);
    }
}
