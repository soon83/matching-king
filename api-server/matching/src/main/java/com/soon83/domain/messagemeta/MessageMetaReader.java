package com.soon83.domain.messagemeta;

import java.util.List;

public interface MessageMetaReader {
    List<MessageMeta> searchMessageMetasOfMember(Long targetMemberId);

}
