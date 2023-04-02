package com.soon83.domain.limit;

import com.soon83.domain.member.Member;
import lombok.Builder;

@Builder
public record LimitSearchConditionQuery(
        Member.Type memberType
) {
}
