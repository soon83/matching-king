package com.soon83.domain.member.matchingcondition;

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
@Table(name = "member_matching_condition")
public class MatchingCondition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int minAge;
    @Column
    private int maxAge;
    @Column
    private Gender gender;
    @Embedded
    private Mbti mbti;

    @Builder
    public MatchingCondition(
            int minAge,
            int maxAge,
            Gender gender,
            Mbti mbti
    ) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.gender = gender;
        this.mbti = mbti;
    }

    public void update(int minAge, int maxAge, Gender gender, Mbti mbti) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        if (gender != null) this.gender = gender;
        if (mbti != null) this.mbti = mbti;
    }
}
