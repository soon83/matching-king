package com.soon83.infrastructure.limit;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.limit.LimitStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LimitStoreImpl implements LimitStore {

    private final LimitRepository limitRepository;

    @Override
    public Limit create(Limit limit) {
        return limitRepository.save(limit);
    }
}
