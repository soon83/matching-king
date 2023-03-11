package com.soon83.infrastructure.member;

import com.soon83.domain.member.condition.MemberCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberConditionRepository extends JpaRepository<MemberCondition, Long> {
}
