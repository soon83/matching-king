package com.soon83.domain.member;

import com.soon83.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter
    public enum Role {
        ADMIN, MANAGER, MEMBER;
    }

    @Getter
    public enum Type {
        FREE, PAID;
    }

    @Getter
    public enum Gender {
        MALE, FEMALE;
    }

    @Getter
    public enum MBTI {
        ESFJ,
        ESFP,
        ENFJ,
        ENFP,
        ESTJ,
        ESTP,
        ENTJ,
        ENTP,
        ISFJ,
        ISFP,
        INFJ,
        INFP,
        ISTJ,
        ISTP,
        INTJ,
        INTP;
    }
}
