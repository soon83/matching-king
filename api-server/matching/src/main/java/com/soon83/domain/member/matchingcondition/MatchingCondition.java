package com.soon83.domain.member.matchingcondition;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.member.Member;
import com.soon83.domain.valuetype.Gender;
import com.soon83.domain.valuetype.Mbti;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public List<Member.Gender> getGenders() {
        List<Member.Gender> genders = new ArrayList<>();
        if (this.gender.isMale()) genders.add(Member.Gender.MALE);
        if (this.gender.isFemale()) genders.add(Member.Gender.FEMALE);
        return genders;
    }

    public List<Member.Mbti> getMbtiList() {
        List<Member.Mbti> mbtiList = new ArrayList<>();
        if (this.mbti.isEsfj()) mbtiList.add(Member.Mbti.ESFJ);
        if (this.mbti.isEsfp()) mbtiList.add(Member.Mbti.ESFP);
        if (this.mbti.isEnfj()) mbtiList.add(Member.Mbti.ENFJ);
        if (this.mbti.isEnfp()) mbtiList.add(Member.Mbti.ENFP);
        if (this.mbti.isEstj()) mbtiList.add(Member.Mbti.ESTJ);
        if (this.mbti.isEstp()) mbtiList.add(Member.Mbti.ESTP);
        if (this.mbti.isEntj()) mbtiList.add(Member.Mbti.ENTJ);
        if (this.mbti.isEntp()) mbtiList.add(Member.Mbti.ENTP);
        if (this.mbti.isIsfj()) mbtiList.add(Member.Mbti.ISFJ);
        if (this.mbti.isIsfp()) mbtiList.add(Member.Mbti.ISFP);
        if (this.mbti.isInfj()) mbtiList.add(Member.Mbti.INFJ);
        if (this.mbti.isInfp()) mbtiList.add(Member.Mbti.INFP);
        if (this.mbti.isIstj()) mbtiList.add(Member.Mbti.ISTJ);
        if (this.mbti.isIstp()) mbtiList.add(Member.Mbti.ISTP);
        if (this.mbti.isIntj()) mbtiList.add(Member.Mbti.INTJ);
        if (this.mbti.isIntp()) mbtiList.add(Member.Mbti.INTP);
        return mbtiList;
    }
}
