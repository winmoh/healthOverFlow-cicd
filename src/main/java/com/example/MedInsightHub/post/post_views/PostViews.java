package com.example.MedInsightHub.post.post_views;


import com.example.MedInsightHub.post.Post;
import com.example.MedInsightHub.user.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostViews {
    @Id
    @SequenceGenerator(
            name = "post_views_sequence",
            sequenceName = "post_views_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_views_sequence"
    )
    private long post_views_id;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;
    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;

    public PostViews(Post post, Doctor doctor) {
        this.post = post;
        this.doctor = doctor;
    }
}
