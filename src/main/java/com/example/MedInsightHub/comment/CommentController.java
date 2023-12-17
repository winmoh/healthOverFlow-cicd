package com.example.MedInsightHub.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "comment")
public class CommentController {

    private CommentService commentService;

    @PostMapping
    public void commentOnPost(@RequestParam long post_id, @RequestParam String comment_text, @RequestParam ReplyTo reply_to){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=0;
        commentService.commentOnPost(post_id,comment_text,doctor_id,reply_to);
    }

    @PutMapping
    public void updateComment(@RequestParam long comment_id, @RequestParam String comment_text){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=0;
        commentService.updateComment(comment_id, comment_text, doctor_id);
    }

    @DeleteMapping
    public void deleteComment(@RequestParam long comment_id){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=0;
        commentService.deleteComment(comment_id, doctor_id);
    }
}
