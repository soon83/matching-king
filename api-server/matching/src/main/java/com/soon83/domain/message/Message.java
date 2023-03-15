package com.soon83.domain.message;

import com.soon83.domain.BaseEntity;
import com.soon83.domain.member.Member;
import com.soon83.domain.message.reply.MessageReply;
import com.soon83.domain.receivemessage.ReceiveMessage;
import com.soon83.exception.message.MessageReplySeriesException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        indexes = {
                @Index(columnList = "createdAt", name = "IX_createdAt"),
        }
)
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1023)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false, foreignKey = @ForeignKey(name = "FK_message_member"))
    private Member sender;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "message")
    private List<MessageReply> messageReplies = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "message")
    private List<ReceiveMessage> receiveMessages = new ArrayList<>();

    @Builder
    public Message(
            String content,
            Member sender
    ) {
        if (content == null) throw new IllegalArgumentException("content");
        if (sender == null) throw new IllegalArgumentException("sender");

        this.content = content;
        this.sender = sender;
    }

    public void validateSenderEqual(Long replyMemberId) {
        if (Objects.equals(this.sender.getId(), replyMemberId)) {
            throw new MessageReplySeriesException();
        }
    }
}
