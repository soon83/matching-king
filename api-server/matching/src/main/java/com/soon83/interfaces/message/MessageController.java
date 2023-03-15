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
     * [쪽지] 단건 등록
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonResponse<MessageDto.RegisterResponse> registerMessage(@RequestBody @Valid MessageDto.RegisterRequest request) {
        log.debug("# registerMessage # request: {}", request);
        var createMessageCommand = request.toCreateMessageCommand();
        Long messageId = messageApplication.registerMessage(createMessageCommand);
        MessageDto.RegisterResponse response = new MessageDto.RegisterResponse(messageId);
        return CommonResponse.success(response);
    }
}
