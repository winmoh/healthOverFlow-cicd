package com.example.MedInsightHub.post;

import com.example.MedInsightHub.cases.Case;
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
    @ManyToOne
    @JoinColumn(name = "case_id", referencedColumnName = "case_id")
    private Case aCase;
    private String title;
    private String post_text_content;
    private List<String> post_images_content;
    private List<String> post_documents_content;
    private PostType post_type;
    private PostStatus post_status;
    private List<String> tags;
    private int views_count;
    private int likes_count;
    private int comments_count;
    private Date date_posted;

    public Post(Doctor doctor, Case aCase, String title, String post_text_content,List<String> post_images_content, List<String> post_documents_content, PostType post_type, PostStatus post_status, List<String> tags, int views_count, int likes_count, int comments_count, Date date_posted) {
        this.doctor = doctor;
        this.aCase = aCase;
        this.title = title;
        this.post_text_content = post_text_content;
        this.post_images_content = post_images_content;
        this.post_documents_content = post_documents_content;
        this.post_type = post_type;
        this.post_status = post_status;
        this.tags = tags;
        this.views_count = views_count;
        this.likes_count = likes_count;
        this.comments_count = comments_count;
        this.date_posted = date_posted;
    }
}
