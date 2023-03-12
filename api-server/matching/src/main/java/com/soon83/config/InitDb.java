package com.soon83.config;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.Member;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import com.soon83.exception.limit.LimitNotFoundException;
import com.soon83.infrastructure.limit.LimitRepository;
import com.soon83.infrastructure.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InitDb implements InitializingBean {

    private final LimitRepository limitRepository;
    private final MemberRepository memberRepository;

    @Override
    public void afterPropertiesSet() {
        // 회원 제약사항 생성
        List<Limit> limits = limitRepository.findAll();
        if (limits.isEmpty()) {
            limitRepository.saveAll(
                    List.of(
                            Limit.builder()
                                    .memberType(Member.Type.FREE)
                                    .sendMessageCount(3)
                                    .sendMessageNotificationCount(5)
                                    .build(),
                            Limit.builder()
                                    .memberType(Member.Type.PAID)
                                    .sendMessageCount(10)
                                    .sendMessageNotificationCount(10)
                                    .build()
                    )
            );
        }

        // 최고관리자 생성
        Optional<Member> adminById = memberRepository.findById(1L);
        if (adminById.isEmpty()) {
            Limit limit = limitRepository.findByMemberType(Member.Type.PAID)
                    .orElseThrow(LimitNotFoundException::new);

            memberRepository.save(Member.builder()
                    .email("2601948@gmail.com")
                    .nickname("DidierDrogba")
                    .gender(Member.Gender.MALE)
                    .mbti(Member.Mbti.ISTP)
                    .type(Member.Type.PAID)
                    .role(Member.Role.ADMIN)
                    .limit(limit)
                    .matchingCondition(MatchingCondition.builder()
                            .minAge(25)
                            .maxAge(50)
                            .gender(Gender.builder()
                                    .male(true)
                                    .female(true)
                                    .build())
                            .mbti(Mbti.builder()
                                    .enfj(true)
                                    .entp(true)
                                    .intj(true)
                                    .intp(true)
                                    .build())
                            .build())
                    .build());
        }
    }
}
