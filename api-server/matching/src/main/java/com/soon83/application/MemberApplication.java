package com.soon83.application;

import com.soon83.domain.member.MemberCommand;
import com.soon83.domain.member.MemberQuery;
import com.soon83.domain.member.MemberService;
import com.soon83.domain.member.matchingcondition.MatchingConditionCommand;
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
            MatchingConditionCommand.CreateMatchingCondition createMatchingConditionCommand
    ) {
        return memberService.registerMember(createMemberCommand, createMatchingConditionCommand);
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
            MatchingConditionCommand.EditMatchingCondition editMatchingConditionCommand
    ) {
        memberService.editMember(memberId, editMemberCommand, editMatchingConditionCommand);
    }

    public void removeMember(Long memberId) {
        memberService.removeMember(memberId);
    }
}
