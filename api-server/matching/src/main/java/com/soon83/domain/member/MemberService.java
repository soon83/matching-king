package com.soon83.domain.member;

import com.soon83.domain.member.model.MemberCommand;
import com.soon83.domain.member.model.MemberQuery;

import java.util.List;

public interface MemberService {
    Long registerMember(MemberCommand.CreateMember command);
    List<MemberQuery.Main> searchMember(MemberQuery.SearchCondition condition);
}
