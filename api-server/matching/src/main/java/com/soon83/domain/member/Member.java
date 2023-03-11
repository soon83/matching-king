package com.soon83.domain.member;

import com.soon83.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Mbti mbti;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(
            String email,
            String password,
            String nickname,
            Gender gender,
            Mbti mbti,
            Type type,
            Role role
    ) {
        if (email == null) throw new IllegalArgumentException("email");
        if (nickname == null) throw new IllegalArgumentException("nickname");
        if (gender == null) throw new IllegalArgumentException("gender");
        if (mbti == null) throw new IllegalArgumentException("mbti");

        this.email = email;
        //this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.mbti = mbti;
        this.type = Type.FREE;
        this.role = Role.MEMBER;
    }

    public void update(String nickname, Gender gender, Mbti mbti) {
        if (nickname != null) this.nickname = nickname;
        if (gender != null) this.gender = gender;
        if (mbti != null) this.mbti = mbti;
    }

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
    public enum Mbti {
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
