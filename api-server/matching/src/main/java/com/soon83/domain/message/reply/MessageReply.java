package com.soon83.domain.message.reply;

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
public class MessageReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1023)
    private String content;
    @Column
    private boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_member_id", foreignKey = @ForeignKey(name = "FK_messageReply_member"))
    private Member replyMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", foreignKey = @ForeignKey(name = "FK_messageReply_message"))
    private Message message;

    public void setMessage(Message message) {
        if(this.message != null) {
            this.message.getMessageReplies().remove(this);
        }
        this.message = message;
        message.getMessageReplies().add(this);
        /*if (!Objects.equals(message.getMessageReplies(), this)) {
        }*/
    }
}
