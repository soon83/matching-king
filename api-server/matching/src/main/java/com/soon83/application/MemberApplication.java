package com.soon83.application;

import com.soon83.domain.member.MemberService;
import com.soon83.domain.member.condition.model.MemberConditionCommand;
import com.soon83.domain.member.matchingcondition.model.MemberMatchingConditionCommand;
import com.soon83.domain.member.model.MemberCommand;
import com.soon83.domain.member.model.MemberQuery;
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
            MemberCommand.CreateMember createMemberCommand,
            MemberConditionCommand.CreateMemberCondition createMemberConditionCommand,
            MemberMatchingConditionCommand.CreateMemberMatchingCondition createMemberMatchingConditionCommand
    ) {
        return memberService.registerMember(createMemberCommand, createMemberConditionCommand, createMemberMatchingConditionCommand);
    }

    public List<MemberQuery.Main> searchMembers(MemberQuery.SearchCondition condition) {
        return memberService.searchMembers(condition);
    }

    public MemberQuery.Main searchMember(Long memberId) {
        return memberService.searchMember(memberId);
    }

    public MemberQuery.Detail searchMemberDetail(Long memberId) {
        return memberService.searchMemberDetail(memberId);
    }

    public void editMember(
            Long memberId,
            MemberCommand.EditMember editMemberCommand,
            MemberConditionCommand.EditMemberCondition editMemberConditionCommand,
            MemberMatchingConditionCommand.EditMemberMatchingCondition editMemberMatchingConditionCommand
    ) {
        memberService.editMember(memberId, editMemberCommand, editMemberConditionCommand, editMemberMatchingConditionCommand);
    }

    public void removeMember(Long memberId) {
        memberService.removeMember(memberId);
    }
}
