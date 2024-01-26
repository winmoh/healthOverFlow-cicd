package com.example.medinsighthub.post;

import com.example.medinsighthub.user.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
<<<<<<< HEAD:src/main/java/com/example/medinsighthub/post/PostRepository.java
    @Query("select p from Post p where p.post_status='Open'")
    List<Post> getOpenPosts();

    @Query("select p from Post p where p.post_status='Answered'")
    List<Post> getResolvedPosts();

=======
    @Query("select p from Post p where p.post_status=Open")
    List<Post> getOpenPosts();

    @Query("select p from Post p where p.post_status=Answered")
    List<Post> getResolvedPosts();

    @Query("select p from Post p where p.post_status<>Closed")
    List<Post> getNotClosedPosts();

>>>>>>> 3c2e4af78cb2a6bc3188f9034532f151b09d0b0a:src/main/java/com/example/MedInsightHub/post/PostRepository.java
    @Query("select p from Post p where p.doctor=?1")
    List<Post> getDoctorPosts(Doctor doctor);
}
