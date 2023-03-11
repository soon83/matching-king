package com.soon83.domain.member.matchingcondition;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import com.soon83.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class MemberMatchingCondition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int minAge;
    @Column
    private int maxAge;
    @Embedded
    private Gender gender;
    @Embedded
    private Mbti mbti;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", unique = true, foreignKey = @ForeignKey(name = "FK_memberMatchingCondition_member"))
    private Member member;
}
