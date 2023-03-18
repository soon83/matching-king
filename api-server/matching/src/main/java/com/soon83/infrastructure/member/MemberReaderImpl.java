package com.soon83.infrastructure.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberReader;
import com.soon83.domain.member.MemberQuery;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import com.soon83.exception.member.MemberAlreadyExistsException;
import com.soon83.exception.member.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberReaderImpl implements MemberReader {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> readAll(MemberQuery.SearchCondition condition) {
        // TODO queryDsl 로 조회하도록 변경, 페이징도 하자
        return memberRepository.findAll();
    }

    @Override
    public List<Member> readLimitMembersByMatchingCondition(Long senderId, MatchingCondition matchingCondition, int limit) {
        return memberRepository.readLimitMembersByMatchingCondition(senderId, matchingCondition, limit);
    }

    @Override
    public Member readById(Long memberId) {
        return memberRepository.findById(memberId)
                .filter(Member::isActivated)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member readMemberDetailById(Long memberId) {
        return memberRepository.readMemberDetailById(memberId)
                .filter(Member::isActivated)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member readByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail)
                .filter(Member::isActivated)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member readMemberMatchingConditionAndLimitById(Long memberId) {
        return memberRepository.readMemberMatchingConditionAndLimitById(memberId)
                .filter(Member::isActivated)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public void checkAlreadyExistsEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberAlreadyExistsException();
        }
    }
}
