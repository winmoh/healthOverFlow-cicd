package com.example.medinsighthub.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentDTO {
    private long comment_id;
    private long doctor_id;
    private String doctor_username;
    private String doctor_firstname;
    private String doctor_lastname;
    private String profile_pic_url;
    private String comment_text_content;
    private LocalDateTime date_commented;
    private boolean comment_text_edited;
    private LocalDateTime date_edited;
    private int likes_count;
    private int replies_count;
}
