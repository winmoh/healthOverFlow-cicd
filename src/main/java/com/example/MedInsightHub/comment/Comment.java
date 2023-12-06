package com.example.MedInsightHub.comment;

import com.example.MedInsightHub.user.Doctor;
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
public class Comment {
    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    private long comment_id;
    @Column(nullable = false,unique = true)
    private long replying_to_id;
    @Enumerated(value = EnumType.STRING)
    private ReplyTo reply_to;
    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
    private String comment_content;
    private Date date_commented;
    private int likes_count;
    private int replies_count;

    public Comment(long replying_to_id, ReplyTo reply_to, Doctor doctor, String comment_content, Date date_commented, int likes_count, int replies_count) {
        this.replying_to_id = replying_to_id;
        this.reply_to = reply_to;
        this.doctor = doctor;
        this.comment_content = comment_content;
        this.date_commented = date_commented;
        this.likes_count = likes_count;
        this.replies_count = replies_count;
    }
}
