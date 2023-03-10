package com.soon83.infrastructure.member;

import com.soon83.domain.member.MemberMatchingCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMatchingConditionRepository extends JpaRepository<MemberMatchingCondition, Long> {
}
