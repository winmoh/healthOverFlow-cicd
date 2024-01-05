package com.example.MedInsightHub.like;

import com.example.MedInsightHub.user.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final DoctorRepository doctorRepository;
    public void likePost(long post_id, long doctor_id) {
        Like like = new Like();
        like.setDoctor(doctorRepository.findById(doctor_id).orElseThrow());
        like.setLike_type(LikeType.Post);
        like.setPost_comment_id(post_id);
        likeRepository.save(like);
        // TODO notify the new like
    }

    public void likeComment(long comment_id, long doctor_id) {
        Like like = new Like();
        like.setDoctor(doctorRepository.findById(doctor_id).orElseThrow());
        like.setLike_type(LikeType.Comment);
        like.setPost_comment_id(comment_id);
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
        likeRepository.deleteById(like_id);
    }
}
