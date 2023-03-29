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
@Table(schema = "member", name = "rm_member_limit")
public class Limit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private Member.Type memberType;
    @Column
    private int sendMessageCount;
    @Column
    private int sendMessageNotificationCount;

    @Builder
    public Limit(
            Member.Type memberType,
            int sendMessageCount,
            int sendMessageNotificationCount
    ) {
        this.memberType = memberType;
        this.sendMessageCount = sendMessageCount;
        this.sendMessageNotificationCount = sendMessageNotificationCount;
    }
}
