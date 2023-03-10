package com.soon83.domain.member;

import com.soon83.domain.member.model.MemberCommand;

public interface MemberService {
    Long registerMember(MemberCommand.CreateMember command);
}
