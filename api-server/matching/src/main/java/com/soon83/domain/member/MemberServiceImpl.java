package com.soon83.domain.member;

import com.soon83.domain.member.model.MemberCommand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberStore memberStore;

    @Override
    @Transactional
    public Long registerMember(MemberCommand.CreateMember command) {
        Member createdMember = memberStore.create(command.toEntity());
        return createdMember.getId();
    }
}
