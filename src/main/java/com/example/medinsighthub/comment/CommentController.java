package com.example.medinsighthub.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "comment")
public class CommentController {
    @Autowired
    private final CommentService commentService;

    @GetMapping
    public List<CommentDTO> getCommentsReplies(@RequestParam(name = "comment_id") long comment_id){
        return commentService.getCommentsReplies(comment_id);
    }

    @PostMapping
    public List<CommentDTO> commentOn(@RequestBody Map<String,Object> comment_input){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=1;
        ReplyTo reply_to = ReplyTo.Post;
        if(comment_input.get("reply_to").equals("Comment")){
            reply_to = ReplyTo.Comment;
        }
        return commentService.commentOn(Long.valueOf(comment_input.get("post_comment_id").toString()),
                (String) comment_input.get("comment_text"),
                doctor_id,
                reply_to);
    }

    @PutMapping
    public void updateComment(@RequestBody Map<String,Object> comment){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=1;
        commentService.updateComment(Long.valueOf(comment.get("comment_id").toString()),
                (String) comment.get("comment_text"),
                doctor_id);
    }

    @DeleteMapping(path = "/{comment_id}")
    public void deleteComment(@PathVariable(name = "comment_id") long comment_id){
        // TODO get doctor id from request token and pass it as parameter
        long doctor_id=1;
        commentService.deleteComment(comment_id, doctor_id);
    }
}
