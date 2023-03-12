package com.soon83.interfaces.message;

import com.soon83.domain.message.MessageCommand;
import com.soon83.domain.message.MessageQuery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

public class MessageDto {

    @Data
    public static class RegisterRequest {
        @NotBlank(message = "필수값")
        @Size(max = 1000, message = "1000 자 이내")
        private String messageContent;
        @NotNull(message = "필수값")
        private Long messageWriterMemberId;

        public MessageCommand.CreateMessage toCreateMessageCommand() {
            return MessageCommand.CreateMessage.builder()
                    .content(messageContent)
                    .writerMemberId(messageWriterMemberId)
                    .build();
        }
    }

    @Data
    public static class CreateResponse {
        private final Long messageId;

        @Builder
        public CreateResponse(Long messageId) {
            this.messageId = messageId;
        }
    }

    @Data
    public static class SearchCondition {
        private String messageContent;
        private Long messageWriterMemberId;

        public MessageQuery.SearchCondition toSearchMessageCondition() {
            return MessageQuery.SearchCondition.builder()
                    .content(messageContent)
                    .writerMemberId(messageWriterMemberId)
                    .build();
        }
    }

    @Data
    public static class Main {
        private final Long messageId;
        private final String messageContent;
        private final Long messageWriterMemberId;

        @Builder
        public Main(
                Long messageId,
                String messageContent,
                Long messageWriterMemberId
        ) {
            this.messageId = messageId;
            this.messageContent = messageContent;
            this.messageWriterMemberId = messageWriterMemberId;
        }

        @Builder
        public Main(MessageQuery.Main messageMain) {
            this.messageId = messageMain.getId();
            this.messageContent = messageMain.getContent();
            this.messageWriterMemberId = messageMain.getWriterMemberId();
        }
    }
}
