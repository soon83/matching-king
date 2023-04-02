package com.soon83.interfaces.member;

import lombok.Builder;

@Builder
public record MemberCreateResponse(
        Long memberId
) {
}
