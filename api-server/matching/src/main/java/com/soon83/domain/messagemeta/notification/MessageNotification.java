package com.soon83.domain.messagemeta.notification;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.member.Member;
import com.soon83.domain.message.Message;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class MessageNotification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private boolean isRead;
    @Column
    private boolean isDeleted;

    public MessageNotification(
            boolean isRead,
            boolean isDeleted
    ) {
        this.isRead = isRead;
        this.isDeleted = isDeleted;
    }
}
