package com.soon83.domain.member;

import com.soon83.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table
public class MemberCondition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int age;
    @Column
    private boolean male;
    @Column
    private boolean female;
    @Column
    private boolean esfj;
    @Column
    private boolean esfp;
    @Column
    private boolean enfj;
    @Column
    private boolean enfp;
    @Column
    private boolean estj;
    @Column
    private boolean estp;
    @Column
    private boolean entj;
    @Column
    private boolean entp;
    @Column
    private boolean isfj;
    @Column
    private boolean isfp;
    @Column
    private boolean infj;
    @Column
    private boolean infp;
    @Column
    private boolean istj;
    @Column
    private boolean istp;
    @Column
    private boolean intj;
    @Column
    private boolean intp;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
