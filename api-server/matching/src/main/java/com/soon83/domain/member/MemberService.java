package com.soon83.domain.member;

import com.soon83.domain.member.condition.model.MemberConditionCommand;
import com.soon83.domain.member.matchingcondition.model.MemberMatchingConditionCommand;
import com.soon83.domain.member.model.MemberCommand;
import com.soon83.domain.member.model.MemberQuery;

import java.util.List;

public interface MemberService {
    Long registerMember(
            MemberCommand.CreateMember createMemberCommand,
            MemberConditionCommand.CreateMemberCondition createMemberConditionCommand,
            MemberMatchingConditionCommand.CreateMemberMatchingCondition createMemberMatchingConditionCommand
    );
    List<MemberQuery.Main> searchMembers(MemberQuery.SearchCondition condition);
    MemberQuery.Main searchMember(Long memberId);
    MemberQuery.Detail searchMemberDetail(Long memberId);
    void editMember(
            Long memberId,
            MemberCommand.EditMember command,
            MemberConditionCommand.EditMemberCondition editMemberConditionCommand,
            MemberMatchingConditionCommand.EditMemberMatchingCondition editMemberMatchingConditionCommand
    );
    void removeMember(Long memberId);

}
