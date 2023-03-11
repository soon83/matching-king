package com.soon83.interfaces.limit;

import com.soon83.domain.limit.model.LimitQuery;
import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;

public class LimitDto {

    @Data
    public static class Main {
        private final Long id;
        private final Member.Type memberType;
        private final int sendMessageCount;

        @Builder
        public Main(LimitQuery.Main main) {
            this.id = main.getId();
            this.memberType = main.getMemberType();
            this.sendMessageCount = main.getSendMessageCount();
        }

        public Main(Long id, Member.Type memberType, int sendMessageCount) {
            this.id = id;
            this.memberType = memberType;
            this.sendMessageCount = sendMessageCount;
        }
    }
}
