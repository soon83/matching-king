package com.soon83.domain.limit;

import com.soon83.domain.limit.model.LimitCommand;
import com.soon83.domain.limit.model.LimitQuery;

import java.util.List;

public interface LimitService {
    Long registerLimit(LimitCommand.CreateLimit command);
    List<LimitQuery.Main> searchLimits();
    LimitQuery.Main searchLimit(Long limitId);
}
