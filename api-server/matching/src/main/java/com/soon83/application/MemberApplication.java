package com.soon83.application;

import com.soon83.domain.member.*;
import com.soon83.domain.member.matchingcondition.MatchingConditionCreateCommand;
import com.soon83.domain.member.matchingcondition.MatchingConditionEditCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberApplication {
    private final MemberService memberService;

    public Long registerMember(
            MemberCreateCommand createMemberCommand,
            MatchingConditionCreateCommand createMatchingConditionCommand
    ) {
        return memberService.registerMember(createMemberCommand, createMatchingConditionCommand);
    }

    public List<MemberQuery> searchMembers(MemberSearchConditionQuery condition) {
        return memberService.searchMembers(condition);
    }

    public MemberQuery searchMember(Long memberId) {
        return memberService.searchMember(memberId);
    }

    public MemberDetailQuery searchMemberDetail(Long memberId) {
        return memberService.searchMemberDetail(memberId);
    }

    public MemberQuery searchMemberByEmail(String memberEmail) {
        return memberService.searchMemberByEmail(memberEmail);
    }

    public void editMember(
            Long memberId,
            MemberEditCommand editMemberCommand,
            MatchingConditionEditCommand editMatchingConditionCommand
    ) {
        memberService.editMember(memberId, editMemberCommand, editMatchingConditionCommand);
    }

    public void removeMember(Long memberId) {
        memberService.removeMember(memberId);
    }
}
