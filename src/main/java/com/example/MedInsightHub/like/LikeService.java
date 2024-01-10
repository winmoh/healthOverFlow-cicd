package com.example.MedInsightHub.like;

import com.example.MedInsightHub.comment.Comment;
import com.example.MedInsightHub.comment.CommentRepository;
import com.example.MedInsightHub.comment.CommentService;
import com.example.MedInsightHub.post.Post;
import com.example.MedInsightHub.post.PostRepository;
import com.example.MedInsightHub.user.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final DoctorRepository doctorRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void likePost(long post_id, long doctor_id) {
        Like like = new Like();
        like.setDoctor(doctorRepository.findById(doctor_id).orElseThrow());
        like.setLike_type(LikeType.Post);
        like.setPost_comment_id(post_id);
        Post post = postRepository.findById(post_id).orElseThrow();
        post.setLikes_count(
                post.getLikes_count()+1
        );
        postRepository.save(post);
        likeRepository.save(like);
        // TODO notify the new like
    }

    public void likeComment(long comment_id, long doctor_id) {
        Like like = new Like();
        like.setDoctor(doctorRepository.findById(doctor_id).orElseThrow());
        like.setLike_type(LikeType.Comment);
        like.setPost_comment_id(comment_id);
        Comment comment = commentRepository.findById(comment_id).orElseThrow();
        comment.setLikes_count(
                comment.getLikes_count()+1
        );
        commentRepository.save(comment);
        likeRepository.save(like);
        // TODO notify the new like
    }


    public void deleteLike(long like_id, long doctor_id) {
        Like like = likeRepository.findById(like_id).orElseThrow(
                () -> new IllegalStateException("like with id "+like_id+" not found!!!")
        );
        if(like.getDoctor().getDoctor_id()!=doctor_id){
            throw new IllegalStateException("doctor with id "+doctor_id+" cannot delete the specified like");
        }
        if (like.getLike_type()==LikeType.Post) {
            Post post = postRepository.findById(like.getPost_comment_id()).orElseThrow();
            post.setLikes_count(
                    post.getLikes_count()-1
            );
            postRepository.save(post);
        } else {
            Comment comment = commentRepository.findById(like.getPost_comment_id()).orElseThrow();
            comment.setLikes_count(
                    comment.getLikes_count()-1
            );
            commentRepository.save(comment);
        }
        likeRepository.deleteById(like_id);
    }
}
