package com.example.MedInsightHub.post;

import com.example.MedInsightHub.user.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("select p from Post p where p.post_status=Open")
    List<Post> getOpenPosts();

    @Query("select p from Post p where p.post_status=Answered")
    List<Post> getResolvedPosts();

    @Query("select p from Post p where p.post_status<>Closed")
    List<Post> getNotClosedPosts();

    @Query("select p from Post p where p.doctor=?1")
    List<Post> getDoctorPosts(Doctor doctor);
}
