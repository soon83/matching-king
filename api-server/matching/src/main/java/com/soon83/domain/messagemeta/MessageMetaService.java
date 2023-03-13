package com.soon83.domain.messagemeta;

import java.util.List;

public interface MessageMetaService {
    List<MessageMetaQuery.Main> searchMessageMetasOfMember(Long targetMemberId);
}
