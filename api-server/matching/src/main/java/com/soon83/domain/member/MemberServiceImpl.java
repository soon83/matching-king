package com.soon83.domain.member;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.limit.LimitReader;
import com.soon83.domain.member.condition.model.MemberConditionCommand;
import com.soon83.domain.member.matchingcondition.model.MemberMatchingConditionCommand;
import com.soon83.domain.member.model.MemberCommand;
import com.soon83.domain.member.model.MemberQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final LimitReader limitReader;
    private final MemberReader memberReader;
    private final MemberStore memberStore;

    @Override
    @Transactional
    public Long registerMember(
            MemberCommand.CreateMember createMemberCommand,
            MemberConditionCommand.CreateMemberCondition createMemberConditionCommand,
            MemberMatchingConditionCommand.CreateMemberMatchingCondition createMemberMatchingConditionCommand
    ) {
        Limit limit = limitReader.readByMemberType(createMemberCommand.getType());
        Member member = createMemberCommand.toEntity(limit, createMemberConditionCommand.toEntity(), createMemberMatchingConditionCommand.toEntity());
        Member createdMember = memberStore.create(member);
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
        Member member = memberReader.readById(memberId);
        return new MemberQuery.Main(member);
    }

    @Override
    public MemberQuery.Detail searchMemberDetail(Long memberId) {
        Member member = memberReader.readMemberDetailById(memberId);
        return new MemberQuery.Detail(member);
    }

    @Override
    @Transactional
    public void editMember(Long memberId, MemberCommand.EditMember command) {
        Member member = memberReader.readById(memberId);
        command.update(member);
        memberStore.update(member); // 없어도 되지만 명시적으로 넣,,
    }

    @Override
    @Transactional
    public void removeMember(Long memberId) {
        Member member = memberReader.readById(memberId);
        memberStore.delete(member);
    }
}
