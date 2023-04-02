package com.soon83.interfaces.message;

import com.soon83.CommonResponse;
import com.soon83.application.MessageApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {
    private final MessageApplication messageApplication;

    /**
     * [쪽지 보내기] 단건 등록
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonResponse<MessageRegisterResponse> registerMessage(@RequestBody @Valid MessageRegisterRequest request) {
        log.debug("# registerMessage # request: {}", request);
        var command = request.toCreateMessageCommand();
        Long messageId = messageApplication.registerMessage(command);
        MessageRegisterResponse response = new MessageRegisterResponse(messageId);
        return CommonResponse.success(response);
    }
}
