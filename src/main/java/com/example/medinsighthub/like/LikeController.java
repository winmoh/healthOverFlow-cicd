package com.example.medinsighthub.like;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping(path = "/post/{post_id}")
    public void likePost(@PathVariable(name = "post_id") long post_id){
        long doctor_id = 1;
        likeService.likePost(post_id,doctor_id);
    }

    @PostMapping(path = "/comment/{comment_id}")
    public void likeComment(@PathVariable(name = "comment_id") long comment_id){
        long doctor_id = 1;
        likeService.likeComment(comment_id,doctor_id);
    }

    @DeleteMapping(path = "delete/{like_id}")
    public void deleteLike(@PathVariable(name = "like_id") long like_id){
        long doctor_id = 1;
        likeService.deleteLike(like_id, doctor_id);
    }
}
