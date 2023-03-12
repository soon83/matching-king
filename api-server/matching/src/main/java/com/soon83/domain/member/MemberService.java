package com.soon83.domain.member;

import com.soon83.domain.member.matchingcondition.MatchingConditionCommand;

import java.util.List;

public interface MemberService {
    Long registerMember(
            MemberCommand.CreateMember createMemberCommand,
            MatchingConditionCommand.CreateMatchingCondition createMatchingConditionCommand
    );
    List<MemberQuery.Main> searchMembers(MemberQuery.SearchCondition condition);
    MemberQuery.Main searchMember(Long memberId);
    MemberQuery.Detail searchMemberDetail(Long memberId);
    void editMember(
            Long memberId,
            MemberCommand.EditMember command,
            MatchingConditionCommand.EditMatchingCondition editMatchingConditionCommand
    );
    void removeMember(Long memberId);

}
