package com.example.medinsighthub.post;

import com.example.medinsighthub.cases.CaseRepository;
import com.example.medinsighthub.comment.CommentDTO;
import com.example.medinsighthub.comment.CommentRepository;
import com.example.medinsighthub.user.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final DoctorRepository doctorRepository;
    private final CommentRepository commentRepository;
    private final CaseRepository caseRepository;

    public List<PostDTO> getOpenPosts() {
        return postRepository.getOpenPosts().stream().map(
                post -> new PostDTO(post.getPost_id(),
                        post.getDoctor().getUser().getFirstname(),
                        post.getDoctor().getUser().getLastname(),
                        post.getTitle(),
                        post.getPost_type(),
                        post.getPost_status(),
                        post.getTags(),
                        post.getViews_count(),
                        post.getLikes_count(),
                        post.getComments_count(),
                        post.getDate_posted()
                        )
        ).collect(Collectors.toList());
    }

    public List<PostDTO> getResolvedPosts() {
        return postRepository.getResolvedPosts().stream().map(
                post -> new PostDTO(post.getPost_id(),
                        post.getDoctor().getUser().getFirstname(),
                        post.getDoctor().getUser().getLastname(),
                        post.getTitle(),
                        post.getPost_type(),
                        post.getPost_status(),
                        post.getTags(),
                        post.getViews_count(),
                        post.getLikes_count(),
                        post.getComments_count(),
                        post.getDate_posted())
        ).collect(Collectors.toList());
    }

    public List<PostDTO> getDoctorPosts(long doctor_id) {
        return postRepository.getDoctorPosts(doctorRepository.findById(doctor_id).orElseThrow()).stream().map(
                post -> new PostDTO(post.getPost_id(),
                        post.getDoctor().getUser().getFirstname(),
                        post.getDoctor().getUser().getLastname(),
                        post.getTitle(),
                        post.getPost_type(),
                        post.getPost_status(),
                        post.getTags(),
                        post.getViews_count(),
                        post.getLikes_count(),
                        post.getComments_count(),
                        post.getDate_posted())
        ).collect(Collectors.toList());
    }


    public List<CommentDTO> getPostComments(long post_id) {
        return commentRepository.commentsOnPost(post_id).stream().map(
                comment -> new CommentDTO(
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

    public Map<String,Object> getPostDetails(long post_id, long doctor_id) {
        Map<String, Object> map = new HashMap<>();
        Post post = postRepository.findById(post_id).orElseThrow(
                ()-> new IllegalStateException("post with id "+post_id+" was not found!!!")
        );
        map.put("post_id",post.getPost_id());
        map.put("title",post.getTitle());
        map.put("views_count",post.getViews_count());
        map.put("comments_count",post.getComments_count());
        map.put("likes_count",post.getLikes_count());
        map.put("doctor_firstname",post.getDoctor().getUser().getFirstname());
        map.put("doctor_lastname",post.getDoctor().getUser().getLastname());
        map.put("doctor_username",post.getDoctor().getUser().getUsername());
        map.put("profile_pic_url",post.getDoctor().getUser().getProfile_pic_url());
        map.put("tags",post.getTags());
        map.put("post_text_content",post.getPost_text_content());
        map.put("include_document",post.isInclude_document());
        map.put("post_document_url",post.getPost_document_url());
        map.put("post_type",post.getPost_type());
        map.put("post_status",post.getPost_status());
        map.put("date_posted",post.getDate_posted());
        map.put("isOwner",isPostOwner(post_id,doctor_id));
        if(isPostOwner(post_id,doctor_id)){
            map.put("case_id",post.getACase().getCase_id());
        }

        return map;
    }

    private boolean isPostOwner(long post_id, long doctor_id){
        return postRepository.findById(post_id).orElseThrow().getDoctor().getDoctor_id()==doctor_id;
    }

    public void closePost(long post_id, long doctor_id) {
        if(!isPostOwner(post_id,doctor_id)){
            throw new IllegalStateException("doctor has not permission to perform the action on post wth id "+post_id);
        }
        Post post = postRepository.findById(post_id).orElseThrow();
        post.setPost_status(PostStatus.Closed);
        postRepository.save(post);
    }

    public void openPost(long post_id, long doctor_id) {
        if(!isPostOwner(post_id,doctor_id)){
            throw new IllegalStateException("doctor has not permission to perform the action on post wth id "+post_id);
        }
        Post post = postRepository.findById(post_id).orElseThrow();
        post.setPost_status(PostStatus.Open);
        postRepository.save(post);
    }

    public void createPost(long doctor_id, Map<String,Object> post_info) {
        Post post = new Post();
        post.setPost_status(PostStatus.Open);
        post.setDate_posted(LocalDateTime.now());
        post.setDoctor(doctorRepository.findById(doctor_id).orElseThrow());
        post.setTitle(post_info.get("title").toString());
        post.setPost_text_content(post_info.get("post_text_content").toString());
        if(post_info.containsKey("post_document_url")){
            post.setPost_document_url(post_info.get("post_document_url").toString());
            post.setInclude_document(true);
        }
        if (post_info.get("post_type").toString().equals("Analysis")) {
            post.setPost_type(PostType.Analysis);
        } else if (post_info.get("post_type").toString().equals("Question")) {
            post.setPost_type(PostType.Question);
        } else {
            post.setPost_type(PostType.Discussion);
        }
        post.setTags((List<String>) post_info.get("tags"));
        post.setViews_count(0);
        post.setLikes_count(0);
        post.setComments_count(0);
        postRepository.save(post);
    }

    public void createPostFromCase(long case_id, long doctor_id, Map<String,Object> post_info) {
        Post post = new Post();
        post.setPost_status(PostStatus.Open);
        post.setDate_posted(LocalDateTime.now());
        post.setDoctor(doctorRepository.findById(doctor_id).orElseThrow());
        post.setTitle(post_info.get("title").toString());
        post.setPost_text_content(post_info.get("post_text_content").toString());
        if(post_info.containsKey("post_document_url")){
            post.setPost_document_url(post_info.get("post_document_url").toString());
            post.setInclude_document(true);
        }
        if (post_info.get("post_type").toString().equals("Analysis")) {
            post.setPost_type(PostType.Analysis);
        } else if (post_info.get("post_type").toString().equals("Question")) {
            post.setPost_type(PostType.Question);
        } else {
            post.setPost_type(PostType.Discussion);
        }
        post.setTags((List<String>) post_info.get("tags"));
        post.setViews_count(0);
        post.setLikes_count(0);
        post.setComments_count(0);
        post.setACase(caseRepository.findById(case_id).orElseThrow());
        postRepository.save(post);
    }

    public void resolvePost(long post_id, long doctor_id) {
        if(!isPostOwner(post_id,doctor_id)){
            throw new IllegalStateException("doctor has not permission to perform the action on post wth id "+post_id);
        }
        Post post = postRepository.findById(post_id).orElseThrow();
        post.setPost_status(PostStatus.Answered);
        postRepository.save(post);
    }
}
