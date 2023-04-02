package com.soon83.domain.member;

import com.soon83.domain.member.matchingcondition.MatchingConditionCommand;

import java.util.List;

public interface MemberService {
    Long registerMember(
            MemberCommand.CreateMember createMemberCommand,
            MatchingConditionCommand.CreateMatchingCondition createMatchingConditionCommand
    );

    List<MemberQuery> searchMembers(MemberSearchConditionQuery condition);

    MemberQuery searchMember(Long memberId);

    MemberDetailQuery searchMemberDetail(Long memberId);

    MemberQuery searchMemberByEmail(String memberEmail);

    void editMember(
            Long memberId,
            MemberCommand.EditMember command,
            MatchingConditionCommand.EditMatchingCondition editMatchingConditionCommand
    );

    void removeMember(Long memberId);
}
