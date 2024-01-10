package com.example.MedInsightHub.post;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class PostDTO {
    private long post_id;
    private String doctor_firstname;
    private String doctor_lastname;
    private String title;
    private PostType post_type;
    private PostStatus post_status;
    private List<String> tags;
    private int views_count;
    private int likes_count;
    private int comments_count;
    private LocalDateTime date_posted;
    private boolean viewed;
    private boolean liked;

}
