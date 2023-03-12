package com.soon83.infrastructure.member;

import com.soon83.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryQuerydsl {
    Optional<Object> findByEmail(String email);
}
