package com.example.MedInsightHub.post.post_views;

import com.example.MedInsightHub.post.Post;
import com.example.MedInsightHub.user.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostViewsRepository extends JpaRepository<PostViews, Long> {

    @Query("select pv from PostViews pv where pv.doctor=?1 and pv.post=?2")
    Optional<PostViews> getByDoctorAndPost(Doctor doctor, Post post);
}
