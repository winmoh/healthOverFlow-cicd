package com.example.medinsighthub.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("select c from Comment c where c.replying_to_id=?1 and c.reply_to=Comment")
    List<Comment> replyingComments(long comment_id);

    @Query("select c from Comment c where c.replying_to_id=?1 and c.reply_to=Post")
    List<Comment> commentsOnPost(long post_id);
}
