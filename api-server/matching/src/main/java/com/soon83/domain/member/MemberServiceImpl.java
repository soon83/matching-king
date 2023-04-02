package com.soon83.domain.member;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.limit.LimitReader;
import com.soon83.domain.member.matchingcondition.MatchingConditionCreateCommand;
import com.soon83.domain.member.matchingcondition.MatchingConditionEditCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final LimitReader limitReader;
    private final MemberReader memberReader;
    private final MemberStore memberStore;

    @Override
    @Transactional
    public Long registerMember(
            MemberCreateCommand createMemberCommand,
            MatchingConditionCreateCommand createMatchingConditionCommand
    ) {
        memberReader.checkAlreadyExistsEmail(createMemberCommand.email());
        Limit limit = limitReader.readByMemberType(createMemberCommand.type());
        String encodedPassword = passwordEncoder.encode(createMemberCommand.password());
        Member member = createMemberCommand.toEntity(limit, createMatchingConditionCommand.toEntity(), encodedPassword);
        Member createdMember = memberStore.create(member);
        return createdMember.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberQuery> searchMembers(MemberSearchConditionQuery condition) {
        return memberReader.readAll(condition).stream()
                .map(MemberQuery::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberQuery searchMember(Long memberId) {
        Member member = memberReader.readById(memberId);
        return new MemberQuery(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDetailQuery searchMemberDetail(Long memberId) {
        Member member = memberReader.readMemberDetailById(memberId);
        return new MemberDetailQuery(member);
    }

    @Override
    public MemberQuery searchMemberByEmail(String memberEmail) {
        Member member = memberReader.readByEmail(memberEmail);
        return new MemberQuery(member);
    }

    @Override
    @Transactional
    public void editMember(
            Long memberId,
            MemberEditCommand editMemberCommand,
            MatchingConditionEditCommand editMatchingConditionCommand
    ) {
        Member member = memberReader.readById(memberId);
        editMemberCommand.update(member);
        editMatchingConditionCommand.update(member);
    }

    @Override
    @Transactional
    public void removeMember(Long memberId) {
        Member member = memberReader.readById(memberId);
        member.delete();
    }
}
