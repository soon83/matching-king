package com.soon83.domain.limit;

import java.util.List;

public interface LimitReader {
    List<Limit> readAll();
    Limit read(Long limitId);
}
