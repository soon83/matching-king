package com.soon83.domain.limit;

import com.soon83.domain.member.Member;
import lombok.Builder;

@Builder
public record LimitQuery(
        Long id,
        Member.Type memberType,
        int sendMessageCount,
        int sendMessageNotificationCount
) {
    public LimitQuery(Limit entity) {
        this(
                entity.getId(),
                entity.getMemberType(),
                entity.getSendMessageCount(),
                entity.getSendMessageNotificationCount()
        );
    }
}
