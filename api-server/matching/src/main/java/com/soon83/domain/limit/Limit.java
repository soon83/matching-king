package com.soon83.domain.limit;

import com.soon83.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member_limit")
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Member.Type memberType;

    @Column
    private int SendMessageCount;
}
