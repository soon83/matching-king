package com.soon83.interfaces.reply;

import com.soon83.CommonResponse;
import com.soon83.application.ReceiveMessageApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receive-messages/{receiveMessageId}/replies")
public class MessageReplyController {

    private final ReceiveMessageApplication receiveMessageApplication;

    /**
     * [쪽지 답장] 단건 등록
     * - 수신된 쪽지에 답장
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonResponse<MessageReplyRegisterResponse> registerMessageReply(
            @PathVariable Long receiveMessageId,
            @RequestBody @Valid MessageReplyRegisterRequest request
    ) {
        log.debug("# registerMessageReply # request: {}", request);
        var command = request.toCreateMessageReplyCommand();
        Long messageReplyId = receiveMessageApplication.registerMessageReply(receiveMessageId, command);
        MessageReplyRegisterResponse response = new MessageReplyRegisterResponse(messageReplyId);
        return CommonResponse.success(response);
    }
}
