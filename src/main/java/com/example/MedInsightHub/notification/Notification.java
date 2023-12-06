package com.example.MedInsightHub.notification;

import com.example.MedInsightHub.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    @SequenceGenerator(
            name = "notification_sequence",
            sequenceName = "notification_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_sequence"
    )
    private long notification_id;
    @OneToOne
    @JoinColumn(name = "user_id_to_notify", referencedColumnName = "user_id")
    private User user_to_notify;
    @OneToOne
    @JoinColumn(name = "user_id_notif_reason", referencedColumnName = "user_id")
    private User user_notif_reason;
    private long notifying_item_id;
    @Enumerated(value = EnumType.STRING)
    private NotificationType notification_type;

    public Notification(User user_to_notify, User user_notif_reason, long notifying_item_id, NotificationType notification_type) {
        this.user_to_notify = user_to_notify;
        this.user_notif_reason = user_notif_reason;
        this.notifying_item_id = notifying_item_id;
        this.notification_type = notification_type;
    }
}
