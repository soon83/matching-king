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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"message_notification_id"}, name = "UIX_messageNotificationId"),
        },
        indexes = {
                @Index(columnList = "hiddenFromSender", name = "IX_hiddenFromSender"),
                @Index(columnList = "hiddenFromTargetMember", name = "IX_hiddenFromTargetMember"),
        }
)
public class ReceiveMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private boolean hiddenFromSender;
    @Column
    private boolean hiddenFromTargetMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_member_id", nullable = false, foreignKey = @ForeignKey(name = "FK_receiveMessage_targetMember"))
    private Member targetMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false, foreignKey = @ForeignKey(name = "FK_receiveMessage_sender"))
    private Member sender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false, foreignKey = @ForeignKey(name = "FK_receiveMessage_message"))
    private Message message;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "message_notification_id", nullable = false, foreignKey = @ForeignKey(name = "FK_receiveMessage_messageNotification"))
    private Notification notification;

    @Builder
    public ReceiveMessage(
            Member sender,
            Member targetMember,
            Message message
    ) {
        if (sender == null) throw new IllegalArgumentException("sender");
        if (targetMember == null) throw new IllegalArgumentException("targetMember");
        if (message == null) throw new IllegalArgumentException("message");

        this.sender = sender;
        this.targetMember = targetMember;
        setMessage(message);
        this.notification = Notification.builder().build();
    }

    public void setMessage(Message message) {
        if(this.message != null) {
            this.message.getReceiveMessages().remove(this);
        }
        this.message = message;
        message.getReceiveMessages().add(this);
    }
}
