package com.soon83.infrastructure.messagemeta;

import com.soon83.domain.messagemeta.MessageMeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageMetaRepository extends JpaRepository<MessageMeta, Long> {
    List<MessageMeta> findByTargetMemberId(Long targetMemberId);
}
