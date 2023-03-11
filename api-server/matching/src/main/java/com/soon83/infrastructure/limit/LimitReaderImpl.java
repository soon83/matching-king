package com.soon83.infrastructure.limit;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.limit.LimitReader;
import com.soon83.domain.member.Member;
import com.soon83.exception.limit.LimitNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LimitReaderImpl implements LimitReader {

    private final LimitRepository limitRepository;

    @Override
    public List<Limit> readAll() {
        return limitRepository.findAll();
    }

    @Override
    public Limit readById(Long limitId) {
        return limitRepository.findById(limitId)
                .orElseThrow(LimitNotFoundException::new);
    }

    @Override
    public Limit readByMemberType(Member.Type memberType) {
        return limitRepository.findByMemberType(memberType)
                .orElseThrow(LimitNotFoundException::new);
    }
}
