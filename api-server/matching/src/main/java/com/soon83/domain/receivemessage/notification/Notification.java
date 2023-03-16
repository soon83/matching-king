package com.soon83.domain.receivemessage.notification;

import com.soon83.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "message_notification",
        indexes = {
                @Index(columnList = "isRead", name = "IX_isRead"),
                @Index(columnList = "isDeleted", name = "IX_isDeleted"),
        }
)
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private boolean isRead;
    @Column
    private boolean isDeleted;

    @Builder
    public Notification(
            boolean isRead,
            boolean isDeleted
    ) {
        this.isRead = isRead;
        this.isDeleted = isDeleted;
    }

    public void toRead() {
        this.isRead = true;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
