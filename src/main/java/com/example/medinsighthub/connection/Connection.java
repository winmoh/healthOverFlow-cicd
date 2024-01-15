package com.example.medinsighthub.connection;

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
public class Connection {
    @Id
    @SequenceGenerator(
            name = "connection_sequence",
            sequenceName = "connection_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "connection_sequence"
    )
    private long connection_id;
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User user_sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User user_receiver;
    private LocalDateTime date_sent;
    @Enumerated(EnumType.STRING)
    private ConnectionStatus connection_status;

    public Connection(User user_sender, User user_receiver, LocalDateTime date_sent, ConnectionStatus connection_status) {
        this.user_sender = user_sender;
        this.user_receiver = user_receiver;
        this.date_sent = date_sent;
        this.connection_status = connection_status;
    }
}
