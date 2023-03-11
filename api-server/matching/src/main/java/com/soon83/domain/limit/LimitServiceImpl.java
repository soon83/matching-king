package com.soon83.domain.limit;

import com.soon83.domain.limit.model.LimitCommand;
import com.soon83.domain.limit.model.LimitQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitReader limitReader;
    private final LimitStore limitStore;

    @Override
    @Transactional
    public Long registerLimit(LimitCommand.CreateLimit command) {
        Limit createdLimit = limitStore.create(command.toEntity());
        return createdLimit.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LimitQuery.Main> searchLimits() {
        return limitReader.readAll().stream()
                .map(LimitQuery.Main::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LimitQuery.Main searchLimit(Long limitId) {
        Limit limit = limitReader.read(limitId);
        return new LimitQuery.Main(limit);
    }
}
