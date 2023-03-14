package com.soon83.domain.valuetype;

import com.soon83.domain.member.Member;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@Embeddable
public class Mbti {

    private boolean esfj = false;
    private boolean esfp = false;
    private boolean enfj = false;
    private boolean enfp = false;
    private boolean estj = false;
    private boolean estp = false;
    private boolean entj = false;
    private boolean entp = false;
    private boolean isfj = false;
    private boolean isfp = false;
    private boolean infj = false;
    private boolean infp = false;
    private boolean istj = false;
    private boolean istp = false;
    private boolean intj = false;
    private boolean intp = false;

    @Builder
    public Mbti(
            boolean esfj,
            boolean esfp,
            boolean enfj,
            boolean enfp,
            boolean estj,
            boolean estp,
            boolean entj,
            boolean entp,
            boolean isfj,
            boolean isfp,
            boolean infj,
            boolean infp,
            boolean istj,
            boolean istp,
            boolean intj,
            boolean intp
    ) {
        this.esfj = esfj;
        this.esfp = esfp;
        this.enfj = enfj;
        this.enfp = enfp;
        this.estj = estj;
        this.estp = estp;
        this.entj = entj;
        this.entp = entp;
        this.isfj = isfj;
        this.isfp = isfp;
        this.infj = infj;
        this.infp = infp;
        this.istj = istj;
        this.istp = istp;
        this.intj = intj;
        this.intp = intp;
    }

    public Mbti(Member.Mbti memberMbti) {
        this.esfj = Member.Mbti.ESFJ == memberMbti;
        this.esfp = Member.Mbti.ESFP == memberMbti;
        this.enfj = Member.Mbti.ENFJ == memberMbti;
        this.enfp = Member.Mbti.ENFP == memberMbti;
        this.estj = Member.Mbti.ESTJ == memberMbti;
        this.estp = Member.Mbti.ESTP == memberMbti;
        this.entj = Member.Mbti.ENTJ == memberMbti;
        this.entp = Member.Mbti.ENTP == memberMbti;
        this.isfj = Member.Mbti.ISFJ == memberMbti;
        this.isfp = Member.Mbti.ISFP == memberMbti;
        this.infj = Member.Mbti.INFJ == memberMbti;
        this.infp = Member.Mbti.INFP == memberMbti;
        this.istj = Member.Mbti.ISTJ == memberMbti;
        this.istp = Member.Mbti.ISTP == memberMbti;
        this.intj = Member.Mbti.INTJ == memberMbti;
        this.intp = Member.Mbti.INTP == memberMbti;
    }

    public List<Member.Mbti> checkedList() {
        List<Member.Mbti> mbtiList = new ArrayList<>();
        if (this.esfj) mbtiList.add(Member.Mbti.ESFJ);
        if (this.esfp) mbtiList.add(Member.Mbti.ESFP);
        if (this.enfj) mbtiList.add(Member.Mbti.ENFJ);
        if (this.enfp) mbtiList.add(Member.Mbti.ENFP);
        if (this.estj) mbtiList.add(Member.Mbti.ESTJ);
        if (this.estp) mbtiList.add(Member.Mbti.ESTP);
        if (this.entj) mbtiList.add(Member.Mbti.ENTJ);
        if (this.entp) mbtiList.add(Member.Mbti.ENTP);
        if (this.isfj) mbtiList.add(Member.Mbti.ISFJ);
        if (this.isfp) mbtiList.add(Member.Mbti.ISFP);
        if (this.infj) mbtiList.add(Member.Mbti.INFJ);
        if (this.infp) mbtiList.add(Member.Mbti.INFP);
        if (this.istj) mbtiList.add(Member.Mbti.ISTJ);
        if (this.istp) mbtiList.add(Member.Mbti.ISTP);
        if (this.intj) mbtiList.add(Member.Mbti.INTJ);
        if (this.intp) mbtiList.add(Member.Mbti.INTP);
        return mbtiList;
    }
}
