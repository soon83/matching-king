package com.soon83.interfaces.limit;

import com.soon83.domain.limit.LimitQuery;
import com.soon83.domain.member.Member;
import lombok.Builder;

@Builder
public record LimitResponse(
        Long id,
        Member.Type memberType,
        int sendMessageCount,
        int sendMessageNotificationCount
) {
    public LimitResponse(LimitQuery query) {
        this(
                query.id(),
                query.memberType(),
                query.sendMessageCount(),
                query.sendMessageNotificationCount()
        );
    }
}
