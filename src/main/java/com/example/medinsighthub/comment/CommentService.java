package com.example.medinsighthub.comment;

import com.example.medinsighthub.post.Post;
import com.example.medinsighthub.post.PostRepository;
import com.example.medinsighthub.user.Doctor;
import com.example.medinsighthub.user.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PostRepository postRepository;

    public List<CommentDTO> getCommentsReplies(long post_comment_id){
        return commentRepository.replyingComments(post_comment_id).stream().map(
                comment ->
                    new CommentDTO(
                            comment.getComment_id(),
                            comment.getDoctor().getDoctor_id(),
                            comment.getDoctor().getUser().getUsername(),
                            comment.getDoctor().getUser().getFirstname(),
                            comment.getDoctor().getUser().getLastname(),
                            comment.getDoctor().getUser().getProfile_pic_url(),
                            comment.getComment_text_content(),
                            comment.getDate_commented(),
                            comment.isComment_text_edited(),
                            comment.getDate_edited(),
                            comment.getLikes_count(),
                            comment.getReplies_count()
                    )
        ).collect(Collectors.toList());
    }

    public List<CommentDTO> commentOn(long post_comment_id, String comment_text, long doctor_id, ReplyTo reply_to) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow();
        Comment replying_to_comment;
        Post replying_to_post;

        {
            if (reply_to==ReplyTo.Comment) {
                replying_to_comment=commentRepository.findById(post_comment_id).orElseThrow(
                        () ->
                            new IllegalStateException("no comment with id "+post_comment_id+" was found!!!")
                );

            }
            else {
                replying_to_post=postRepository.findById(post_comment_id).orElseThrow(
                        () ->
                                new IllegalStateException("no post with id "+post_comment_id+" was found!!!")
                );
            }
        }
        Comment comment = new Comment();
        comment.setComment_text_content(comment_text);
        comment.setDoctor(doctor);
        comment.setReplying_to_id(post_comment_id);
        comment.setLikes_count(0);
        comment.setReply_to(reply_to);
        comment.setReplies_count(0);
        comment.setDate_commented(LocalDateTime.now());
        commentRepository.save(comment);
        if (reply_to==ReplyTo.Comment) {
            replying_to_comment = commentRepository.findById(post_comment_id).orElseThrow();
            replying_to_comment.setReplies_count(replying_to_comment.getReplies_count()+1);
            commentRepository.save(replying_to_comment);
        } else {
             replying_to_post = postRepository.findById(post_comment_id).orElseThrow();
            replying_to_post.setComments_count(replying_to_post.getComments_count()+1);
            postRepository.save(replying_to_post);
        }
        return List.of(new CommentDTO(
                comment.getComment_id(),
                comment.getDoctor().getDoctor_id(),
                comment.getDoctor().getUser().getUsername(),
                comment.getDoctor().getUser().getFirstname(),
                comment.getDoctor().getUser().getLastname(),
                comment.getDoctor().getUser().getProfile_pic_url(),
                comment.getComment_text_content(),
                comment.getDate_commented(),
                comment.isComment_text_edited(),
                comment.getDate_edited(),
                comment.getLikes_count(),
                comment.getReplies_count()
        ));
        // TODO notify the comment or post owner about the reply (NewComment or NewReply)
    }

    public boolean commentBelongsToDoctor(long comment_id, long doctor_id){
        Comment comment = commentRepository.findById(comment_id).orElseThrow();
        return comment.getDoctor().getUser().getUser_id()==doctor_id;
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
        // TODO remove all likes related the comment
        List<Comment> replying_comments = commentRepository.replyingComments(comment_id);
        for(Comment reply : replying_comments){
            deleteComment(reply.getComment_id());
        }
        Comment comment = commentRepository.findById(comment_id).orElseThrow();
        if (comment.getReply_to()==ReplyTo.Comment) {
            Comment reply_to_comment = commentRepository.findById(comment.getReplying_to_id()).orElseThrow();
            reply_to_comment.setReplies_count(reply_to_comment.getReplies_count()-1);
            commentRepository.save(reply_to_comment);
        } else {
            Post reply_to_post = postRepository.findById(comment.getReplying_to_id()).orElseThrow();
            reply_to_post.setComments_count(reply_to_post.getComments_count()-1);
            postRepository.save(reply_to_post);
        }
        commentRepository.deleteById(comment_id);
    }
}
