package com.soon83.domain.receivemessage;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.member.Member;
import com.soon83.domain.message.Message;
import com.soon83.domain.receivemessage.notification.Notification;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class ReceiveMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_member_id", foreignKey = @ForeignKey(name = "FK_receiveMessage_member"))
    private Member targetMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", foreignKey = @ForeignKey(name = "FK_receiveMessage_message"))
    private Message message;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_receiveMessage_messageNotification"))
    private Notification notification;

    @Builder
    public ReceiveMessage(
            Member targetMember,
            Message message,
            Notification notification
    ) {
        if (targetMember == null) throw new IllegalArgumentException("targetMember");
        if (message == null) throw new IllegalArgumentException("message");
        if (notification == null) throw new IllegalArgumentException("notification");

        this.targetMember = targetMember;
        setMessage(message);
        this.notification = notification;
    }

    public void setMessage(Message message) {
        if(this.message != null) {
            this.message.getReceiveMessages().remove(this);
        }
        this.message = message;
        message.getReceiveMessages().add(this);
    }
}
