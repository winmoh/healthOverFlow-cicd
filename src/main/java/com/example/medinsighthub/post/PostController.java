package com.example.medinsighthub.post;

import com.example.medinsighthub.comment.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/post")
public class PostController {
    @Autowired
    private final PostService postService;

    @GetMapping(path = "open")
    public List<PostDTO> getOpenPosts(){
        return postService.getOpenPosts();
    }

    @GetMapping(path = "resolved")
    public List<PostDTO> getResolvedPosts(){
        return postService.getResolvedPosts();
    }

    @GetMapping(path = "myPosts")
    public List<PostDTO> getDoctorPosts(){
        // TODO get doctor id
        long doctor_id =1;
        return postService.getDoctorPosts(doctor_id);
    }

    @GetMapping(path = "/{post_id}")
    public Map<String,Object> getPostDetails(@PathVariable(name = "post_id") long post_id){
        long doctor_id = 1 ;
        return postService.getPostDetails(post_id,doctor_id);
    }

    @GetMapping(path = "/{post_id}/comments")
    public List<CommentDTO> getPostComments(@PathVariable(name = "post_id") long post_id){
        return postService.getPostComments(post_id);
    }

    @PutMapping(path = "close/{post_id}")
    public void closePost(@PathVariable(name = "post_id") long post_id){
        long doctor_id = 1 ;
        postService.closePost(post_id,doctor_id);
    }

    @PutMapping(path = "open/{post_id}")
    public void openPost(@PathVariable(name = "post_id") long post_id){
        long doctor_id = 1 ;
        postService.openPost(post_id,doctor_id);
    }

    @PutMapping(path = "resolved/{post_id}")
    public void resolvePost(@PathVariable(name = "post_id") long post_id){
        long doctor_id = 1;
        postService.resolvePost(post_id,doctor_id);
    }

    @PostMapping(path = "create/{case_id}")
    public void createPost(@PathVariable(name = "case_id") long case_id,
                           @RequestBody Map<String,Object> post_info){
        long doctor_id = 1 ;
        if (case_id==0) postService.createPost(doctor_id, post_info);
        else postService.createPostFromCase(case_id, doctor_id, post_info);
    }
}
