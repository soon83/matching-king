package com.soon83.domain.member;

import com.soon83.domain.member.model.MemberCommand;
import com.soon83.domain.member.model.MemberQuery;

import java.util.List;

public interface MemberService {
    Long registerMember(MemberCommand.CreateMember command);
    List<MemberQuery.Main> searchMembers(MemberQuery.SearchCondition condition);
    MemberQuery.Main searchMember(Long memberId);
    void editMember(Long memberId, MemberCommand.EditMember command);
    void removeMember(Long memberId);
}
