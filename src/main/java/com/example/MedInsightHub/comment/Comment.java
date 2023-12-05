package com.example.MedInsightHub.comment;

import com.example.MedInsightHub.user.Doctor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private long comment_id;
    private long replying_to_id;
    private ReplyTo reply_to;
    private Doctor doctor;
    private String comment_content;
    private Date date_commented;
    private int likes_count;
}
