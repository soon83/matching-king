package com.soon83.domain.message;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.member.Member;
import com.soon83.domain.notification.MessageNotification;
import com.soon83.domain.message.reply.MessageReply;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1023)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", foreignKey = @ForeignKey(name = "FK_message_member"))
    private Member sender;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "message")
    private List<MessageReply> messageReplies = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "message")
    private List<MessageNotification> messageNotifications = new ArrayList<>();

    @Builder
    public Message(
            String content,
            Member sender
    ) {
        this.content = content;
        this.sender = sender;
    }
}
