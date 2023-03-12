package com.soon83.domain.message.notification;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.member.Member;
import com.soon83.domain.message.Message;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_member_id", foreignKey = @ForeignKey(name = "FK_messageNotification_member"))
    private Member targetMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", foreignKey = @ForeignKey(name = "FK_messageNotification_message"))
    private Message message;

    public void setMessage(Message message) {
        if(this.message != null) {
            this.message.getMessageNotifications().remove(this);
        }
        this.message = message;
        message.getMessageNotifications().add(this);
    }
}
