package com.example.MedInsightHub.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "comment")
public class CommentController {

    private CommentService commentService;

    @GetMapping
    public List<Comment> getCommentsReplies(@RequestParam long post_comment_id){
        return commentService.getCommentsReplies(post_comment_id);
    }

    @PostMapping
    public void commentOn(@RequestParam long post_comment_id, @RequestParam String comment_text, @RequestParam ReplyTo reply_to){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=0;
        commentService.commentOn(post_comment_id,comment_text,doctor_id,reply_to);
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
