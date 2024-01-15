package com.example.medinsighthub.like;

import com.example.medinsighthub.user.Doctor;
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
public class Like {
    @Id
    @SequenceGenerator(
            name = "like_sequence",
            sequenceName = "like_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "like_sequence"
    )
    private long like_id;
    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
    private long post_comment_id;
    @Enumerated(value = EnumType.STRING)
    private LikeType like_type;

    public Like(Doctor doctor, long post_comment_id, LikeType like_type) {
        this.doctor = doctor;
        this.post_comment_id = post_comment_id;
        this.like_type = like_type;
    }
}
