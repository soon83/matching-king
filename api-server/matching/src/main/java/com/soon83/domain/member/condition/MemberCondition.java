package com.soon83.domain.member.condition;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public MemberCondition(
            int age,
            Gender gender,
            Mbti mbti
    ) {
        if (gender == null) throw new IllegalArgumentException("gender");
        if (mbti == null) throw new IllegalArgumentException("mbti");

        this.age = age;
        this.gender = gender;
        this.mbti = mbti;
    }

    public void update(int age, Gender gender, Mbti mbti) {
        this.age = age;
        if (gender != null) this.gender = gender;
        if (mbti != null) this.mbti = mbti;
    }
}
