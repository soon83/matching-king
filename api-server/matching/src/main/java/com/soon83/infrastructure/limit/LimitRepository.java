package com.soon83.infrastructure.limit;

import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findByMemberType(Member.Type paid);
}
