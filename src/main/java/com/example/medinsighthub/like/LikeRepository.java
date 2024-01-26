package com.example.medinsighthub.like;

import com.example.MedInsightHub.user.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query("select l from Like l where l.post_comment_id=?1")
    List<Like> getLikesByCommentOrPost(long post_comment_id);

    @Query("select l from Like l where l.post_comment_id=?1 and l.doctor=?2")
    Optional<Like> getLikeByPostCommentAndDoctor(Long post_comment_id, Doctor doctor);

}
