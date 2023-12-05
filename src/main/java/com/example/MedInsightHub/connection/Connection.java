package com.example.MedInsightHub.connection;

import com.example.MedInsightHub.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    @OneToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User user_sender;
    @OneToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User user_receiver;
    private Date date_sent;
    private ConnectionStatus connection_status;

    public Connection(User user_sender, User user_receiver, Date date_sent, ConnectionStatus connection_status) {
        this.user_sender = user_sender;
        this.user_receiver = user_receiver;
        this.date_sent = date_sent;
        this.connection_status = connection_status;
    }
}
