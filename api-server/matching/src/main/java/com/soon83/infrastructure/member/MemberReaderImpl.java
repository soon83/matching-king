package com.soon83.infrastructure.member;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberReader;
import com.soon83.domain.member.model.MemberQuery;
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
    public List<Member> read(MemberQuery.SearchCondition condition) {
        // TODO queryDsl 사용해서 search condition 으로 조회하고 싶어요
        return memberRepository.findAll();
    }

    @Override
    public Member read(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }
}
