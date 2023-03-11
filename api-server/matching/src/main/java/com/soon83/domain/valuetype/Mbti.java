package com.soon83.domain.valuetype;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
}
