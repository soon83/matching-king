package com.soon83.application;

import com.soon83.domain.member.MemberService;
import com.soon83.domain.member.model.MemberCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberApplication {

    private final MemberService memberService;

    public Long registerMember(MemberCommand.CreateMember command) {
        return memberService.registerMember(command);
    }
}
