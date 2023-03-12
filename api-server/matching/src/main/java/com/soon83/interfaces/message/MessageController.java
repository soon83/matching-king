package com.soon83.interfaces.message;

import com.soon83.CommonResponse;
import com.soon83.application.MessageApplication;
import com.soon83.domain.message.MessageQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageApplication messageApplication;

    /**
     * [메시지] 단건 등록
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommonResponse<MessageDto.CreateResponse> registerMessage(@RequestBody @Valid MessageDto.RegisterRequest request) {
        log.debug("# registerMessage # request: {}", request);
        var createMessageCommand = request.toCreateMessageCommand();
        Long messageId = messageApplication.registerMessage(createMessageCommand);
        MessageDto.CreateResponse response = new MessageDto.CreateResponse(messageId);
        return CommonResponse.success(response);
    }

    /**
     * [메시지] 목록 조회
     */
    @GetMapping
    public CommonResponse<List<MessageDto.Main>> searchMessages(@ModelAttribute @Valid MessageDto.SearchCondition request) {
        log.debug("# searchMessages # request: {}", request);
        MessageQuery.SearchCondition condition = request.toSearchMessageCondition();
        List<MessageQuery.Main> messageList = messageApplication.searchMessages(condition);
        List<MessageDto.Main> response = messageList.stream()
                .map(MessageDto.Main::new)
                .toList();
        return CommonResponse.success(response);
    }
}
