package com.soon83.domain.member;

import com.soon83.config.enumcode.EnumMapperType;
import com.soon83.domain.BaseEntity;
import com.soon83.domain.limit.Limit;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        schema = "member", name = "rm_member",
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
    @RequiredArgsConstructor
    public enum Role implements EnumMapperType {
        ADMIN("관리자"),
        MANAGER("운영자"),
        MEMBER("회원");

        private final String title;

        @Override
        public String getCode() {
            return this.name();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum Type implements EnumMapperType {
        FREE("무료회원"),
        PAID("유료회원");

        private final String title;

        @Override
        public String getCode() {
            return this.name();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum Gender implements EnumMapperType {
        MALE("남성"),
        FEMALE("여성");

        private final String title;

        @Override
        public String getCode() {
            return this.name();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum Mbti implements EnumMapperType {
        ESFJ("ESFJ"),
        ESFP("ESFP"),
        ENFJ("ENFJ"),
        ENFP("ENFP"),
        ESTJ("ESTJ"),
        ESTP("ESTP"),
        ENTJ("ENTJ"),
        ENTP("ENTP"),
        ISFJ("ISFJ"),
        ISFP("ISFP"),
        INFJ("INFJ"),
        INFP("INFP"),
        ISTJ("ISTJ"),
        ISTP("ISTP"),
        INTJ("INTJ"),
        INTP("INTP");

        private final String title;

        @Override
        public String getCode() {
            return this.name();
        }
    }
}
