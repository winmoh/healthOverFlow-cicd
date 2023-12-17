package com.example.MedInsightHub.comment;

import com.example.MedInsightHub.user.Doctor;
import com.example.MedInsightHub.user.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private DoctorRepository doctorRepository;

    public void commentOnPost(long post_id, String comment_text, long doctor_id, ReplyTo reply_to) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow();
        Comment comment = new Comment();
        comment.setComment_text_content(comment_text);
        comment.setDoctor(doctor);
        comment.setReplying_to_id(post_id);
        comment.setLikes_count(0);
        comment.setReply_to(reply_to);
        comment.setReplies_count(0);
        comment.setDate_commented(LocalDateTime.now());
        commentRepository.save(comment);
        // TODO notify the comment or post owner about the reply (NewComment or NewReply)
    }

    public boolean commentBelongsToDoctor(long comment_id, long doctor_id){
        Comment comment = commentRepository.findById(comment_id).orElseThrow();
        return comment.getDoctor().getUser().getId()==doctor_id;
    }

    public void updateComment(long comment_id, String comment_text, long doctor_id) {
        if(!commentBelongsToDoctor(comment_id,doctor_id)){
            throw new IllegalStateException("cannot update comment \n the doc requesting update is not the owner");
        }
        Comment comment = commentRepository.findById(comment_id).orElseThrow();
        comment.setComment_text_content(comment_text);
        comment.setComment_text_edited(true);
        comment.setDate_edited(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public void deleteComment(long comment_id, long doctor_id) {
        if(!commentBelongsToDoctor(comment_id,doctor_id)){
            throw new IllegalStateException("cannot delete comment \n the doc requesting update is not the owner");
        }
        deleteComment(comment_id);
    }

    private void deleteComment(long comment_id){
        List<Comment> replying_comments = commentRepository.replyingComments(comment_id);
        for(Comment reply : replying_comments){
            deleteComment(reply.getComment_id());
        }
        commentRepository.deleteById(comment_id);
    }
}
