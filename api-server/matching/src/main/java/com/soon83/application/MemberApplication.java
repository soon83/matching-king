package com.soon83.application;

import com.soon83.domain.member.MemberService;
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

    public Long registerMember(MemberCommand.CreateMember command) {
        return memberService.registerMember(command);
    }

    public List<MemberQuery.Main> searchMembers(MemberQuery.SearchCondition condition) {
        return memberService.searchMembers(condition);
    }

    public MemberQuery.Main searchMember(Long memberId) {
        return memberService.searchMember(memberId);
    }
}
