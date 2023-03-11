package com.soon83.domain.member.condition;

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
public class MemberCondition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int age;
    @Embedded
    private Gender gender;
    @Embedded
    private Mbti mbti;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", unique = true, foreignKey = @ForeignKey(name = "FK_memberCondition_member"))
    private Member member;
}
