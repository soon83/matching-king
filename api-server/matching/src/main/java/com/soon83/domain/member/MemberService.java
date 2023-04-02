package com.soon83.domain.member;

import com.soon83.domain.member.matchingcondition.MatchingConditionCreateCommand;
import com.soon83.domain.member.matchingcondition.MatchingConditionEditCommand;

import java.util.List;

public interface MemberService {
    Long registerMember(
            MemberCreateCommand createMemberCommand,
            MatchingConditionCreateCommand createMatchingConditionCommand
    );

    List<MemberQuery> searchMembers(MemberSearchConditionQuery condition);

    MemberQuery searchMember(Long memberId);

    MemberDetailQuery searchMemberDetail(Long memberId);

    MemberQuery searchMemberByEmail(String memberEmail);

    void editMember(
            Long memberId,
            MemberEditCommand command,
            MatchingConditionEditCommand editMatchingConditionCommand
    );

    void removeMember(Long memberId);
}
