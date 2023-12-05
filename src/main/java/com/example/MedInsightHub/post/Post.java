package com.example.MedInsightHub.post;

import com.example.MedInsightHub.user.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private long post_id;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
    private String title;
    private String post_content;
    private PostType post_type;
    private PostStatus status;
    private List<String> tags;
    private int views_count;
    private int likes_count;
    private int comments_count;
    private Date date_posted;

    public Post(Doctor doctor, String title, String post_content, PostType post_type, PostStatus status, List<String> tags, int views_count, int likes_count, int comments_count, Date date_posted) {
        this.doctor = doctor;
        this.title = title;
        this.post_content = post_content;
        this.post_type = post_type;
        this.status = status;
        this.tags = tags;
        this.views_count = views_count;
        this.likes_count = likes_count;
        this.comments_count = comments_count;
        this.date_posted = date_posted;
    }
}
