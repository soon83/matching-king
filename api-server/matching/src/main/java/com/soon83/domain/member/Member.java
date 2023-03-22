package com.soon83.domain.member;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "rm_member",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"matching_condition_id"}, name = "UIX_matchingConditionId"),
        },
        indexes = {
                @Index(columnList = "email", name = "IX_email"),
                @Index(columnList = "age", name = "IX_age"),
                @Index(columnList = "mbti", name = "IX_mbti"),
        }
)
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
    private int age;
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
    @Column
    private boolean isActivated;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "limit_id", nullable = false, foreignKey = @ForeignKey(name = "FK_memberLimit"))
    private Limit limit;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "matching_condition_id", nullable = false, foreignKey = @ForeignKey(name = "FK_member_memberMatchingCondition"))
    private MatchingCondition matchingCondition;

    @Builder
    public Member(
            String email,
            String password,
            String nickname,
            int age,
            Gender gender,
            Mbti mbti,
            Type type,
            Role role,
            Limit limit,
            MatchingCondition matchingCondition
    ) {
        if (email == null) throw new IllegalArgumentException("email");
        if (password == null) throw new IllegalArgumentException("password");
        if (nickname == null) throw new IllegalArgumentException("nickname");
        if (gender == null) throw new IllegalArgumentException("gender");
        if (mbti == null) throw new IllegalArgumentException("mbti");
        if (type == null) throw new IllegalArgumentException("type");
        if (role == null) throw new IllegalArgumentException("role");
        if (limit == null) throw new IllegalArgumentException("limit");
        if (matchingCondition == null) throw new IllegalArgumentException("matchingCondition");

        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.mbti = mbti;
        this.type = type;
        this.role = role;
        this.limit = limit;
        this.matchingCondition = matchingCondition;
        this.isActivated = true;
    }

    public void update(String nickname, int age, Gender gender, Mbti mbti) {
        if (nickname != null) this.nickname = nickname;
        this.age = age;
        if (gender != null) this.gender = gender;
        if (mbti != null) this.mbti = mbti;
    }

    public void delete() {
        this.email = String.format("%s_%s", this.email, UUID.randomUUID());
        this.isActivated = false;
    }

    @Getter
    public enum Role {
        ADMIN, MANAGER, MEMBER
    }

    @Getter
    public enum Type {
        FREE, PAID
    }

    @Getter
    public enum Gender {
        MALE, FEMALE
    }

    @Getter
    public enum Mbti {
        ESFJ, ESFP, ENFJ, ENFP, ESTJ, ESTP, ENTJ, ENTP, ISFJ, ISFP, INFJ, INFP, ISTJ, ISTP, INTJ, INTP
    }
}
