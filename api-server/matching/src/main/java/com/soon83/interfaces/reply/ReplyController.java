package com.soon83.interfaces.reply;

import com.soon83.CommonResponse;
import com.soon83.interfaces.message.MessageDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members/{memberId}/receive-messages/{receiveMessageId}/replies")
public class ReplyController {

    /**
     * [답장] 단건 등록
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonResponse<MessageDto.CreateResponse> registerReply(@RequestBody @Valid MessageDto.RegisterRequest request) {
        /*log.debug("# registerReply # request: {}", request);
        var createMessageCommand = request.toCreateMessageCommand();
        Long messageId = messageApplication.registerMessage(createMessageCommand);
        MessageDto.CreateResponse response = new MessageDto.CreateResponse(messageId);
        return CommonResponse.success(response);*/
        return null;
    }
}
