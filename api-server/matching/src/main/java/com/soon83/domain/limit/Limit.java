package com.soon83.domain.limit;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member_limit")
public class Limit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private Member.Type memberType;
    @Column
    private int SendMessageCount;

    @Builder
    public Limit(
            Member.Type memberType,
            int sendMessageCount
    ) {
        this.memberType = memberType;
        this.SendMessageCount = sendMessageCount;
    }
}
