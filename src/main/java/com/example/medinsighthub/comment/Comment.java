package com.example.medinsighthub.comment;

import com.example.medinsighthub.user.Doctor;
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
    @Column(nullable = false)
    private long replying_to_id;
    @Enumerated(value = EnumType.STRING)
    private ReplyTo reply_to;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
    private String comment_text_content;
    private LocalDateTime date_commented;
    private boolean comment_text_edited;
    private LocalDateTime date_edited;
    private int likes_count;
    private int replies_count;

    public Comment(long replying_to_id, ReplyTo reply_to, Doctor doctor, String comment_text_content, LocalDateTime date_commented, boolean comment_text_edited, LocalDateTime date_edited, int likes_count, int replies_count) {
        this.replying_to_id = replying_to_id;
        this.reply_to = reply_to;
        this.doctor = doctor;
        this.comment_text_content = comment_text_content;
        this.date_commented = date_commented;
        this.comment_text_edited = comment_text_edited;
        this.date_edited = date_edited;
        this.likes_count = likes_count;
        this.replies_count = replies_count;
    }
}
