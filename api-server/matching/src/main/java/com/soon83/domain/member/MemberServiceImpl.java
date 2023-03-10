package com.soon83.domain.member;

import com.soon83.domain.member.model.MemberCommand;
import com.soon83.infrastructure.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long createMember(MemberCommand.CreateMember command) {
        Member createdMember = memberRepository.save(command.toEntity());
        return createdMember.getId();
    }
}
