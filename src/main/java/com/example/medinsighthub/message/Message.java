package com.example.medinsighthub.message;

import com.example.medinsighthub.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private long message_id;
    @OneToOne
    @JoinColumn( name = "sender_id", referencedColumnName = "user_id")
    private User user_sender;
    @OneToOne
    @JoinColumn( name = "receiver_id", referencedColumnName = "user_id")
    private User user_receiver;
    private LocalDateTime date_sent;
    private String text_message;
    private boolean message_received;
    private LocalDateTime message_received_at;
    private boolean message_read;
    private LocalDateTime message_read_at;
    private boolean replies_to_message;
    private long reply_to_message_id;

    public Message(User user_sender, User user_receiver, LocalDateTime date_sent, String text_message, boolean message_received, LocalDateTime message_received_at, boolean message_read, LocalDateTime message_read_at, boolean replies_to_message, long reply_to_message_id) {
        this.user_sender = user_sender;
        this.user_receiver = user_receiver;
        this.date_sent = date_sent;
        this.text_message = text_message;
        this.message_received = message_received;
        this.message_received_at = message_received_at;
        this.message_read = message_read;
        this.message_read_at = message_read_at;
        this.replies_to_message = replies_to_message;
        this.reply_to_message_id = reply_to_message_id;
    }
}
