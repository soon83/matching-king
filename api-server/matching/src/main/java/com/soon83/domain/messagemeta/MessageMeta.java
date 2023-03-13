package com.soon83.domain.messagemeta;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.member.Member;
import com.soon83.domain.member.matchingcondition.MatchingCondition;
import com.soon83.domain.message.Message;
import com.soon83.domain.messagemeta.notification.MessageNotification;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class MessageMeta extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_member_id", foreignKey = @ForeignKey(name = "FK_messageMeta_member"))
    private Member targetMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", foreignKey = @ForeignKey(name = "FK_messageMeta_message"))
    private Message message;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "message_notification_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_messageMeta_messageNotification"))
    private MessageNotification messageNotification;

    @Builder
    public MessageMeta(
            Member targetMember,
            Message message
    ) {
        this.targetMember = targetMember;
        setMessage(message);
    }

    public void setMessage(Message message) {
        if(this.message != null) {
            this.message.getMessageMetas().remove(this);
        }
        this.message = message;
        message.getMessageMetas().add(this);
    }
}
