package com.soon83.domain.member;

import com.soon83.domain.member.model.MemberCommand;
import com.soon83.domain.member.model.MemberQuery;
import com.soon83.exception.member.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberReader memberReader;
    private final MemberStore memberStore;

    @Override
    @Transactional
    public Long registerMember(MemberCommand.CreateMember command) {
        Member createdMember = memberStore.create(command.toEntity());
        return createdMember.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberQuery.Main> searchMembers(MemberQuery.SearchCondition condition) {
        return memberReader.read(condition).stream()
                .map(MemberQuery.Main::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberQuery.Main searchMember(Long memberId) {
        Member member = memberReader.read(memberId);
        return new MemberQuery.Main(member);
    }
}
