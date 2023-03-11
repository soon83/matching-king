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
        return memberReader.readAll(condition).stream()
                .map(MemberQuery.Main::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberQuery.Main searchMember(Long memberId) {
        Member member = memberReader.read(memberId);
        return new MemberQuery.Main(member);
    }

    @Override
    @Transactional
    public void editMember(Long memberId, MemberCommand.EditMember command) {
        Member member = memberReader.read(memberId);
        command.update(member);
        memberStore.update(member); // 없어도 되지만 명시적으로 넣,,
    }

    @Override
    @Transactional
    public void removeMember(Long memberId) {
        Member member = memberReader.read(memberId);
        memberStore.delete(member);
    }
}
