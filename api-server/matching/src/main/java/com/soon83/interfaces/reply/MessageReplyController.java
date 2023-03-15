package com.soon83.interfaces.reply;

import com.soon83.CommonResponse;
import com.soon83.application.MessageApplication;
import com.soon83.interfaces.message.MessageDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members/{memberId}/messages/{messageId}/replies")
public class MessageReplyController {

    private final MessageApplication messageApplication;

    /**
     * [답장] 단건 등록
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonResponse<MessageReplyDto.RegisterResponse> registerMessageReply(
            @PathVariable Long memberId,
            @PathVariable Long messageId,
            @RequestBody @Valid MessageReplyDto.RegisterRequest request
    ) {
        log.debug("# registerMessageReply # request: {}", request);
        var createMessageReplyCommand = request.toCreateMessageReplyCommand(memberId, messageId);
        Long messageReplyId = messageApplication.registerMessageReply(createMessageReplyCommand);
        MessageReplyDto.RegisterResponse response = new MessageReplyDto.RegisterResponse(messageReplyId);
        return CommonResponse.success(response);
    }
}
