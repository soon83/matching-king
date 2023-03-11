package com.soon83.infrastructure.member;

import com.soon83.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryQuerydsl {
}
