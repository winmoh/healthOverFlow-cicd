package com.example.MedInsightHub.post;

import com.example.MedInsightHub.comment.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/post")
public class PostController {
    private final PostService postService;

    @GetMapping(path = "all")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<PostDTO> getNotClosedPosts(){
        long doctor_id = 1;
        return postService.getNotClosedPosts(doctor_id);
    }

    @GetMapping(path = "open")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<PostDTO> getOpenPosts(){
        long doctor_id = 1;
        return postService.getOpenPosts(doctor_id);
    }

    @GetMapping(path = "resolved")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<PostDTO> getResolvedPosts(){
        long doctor_id =1;
        return postService.getResolvedPosts(doctor_id);
    }

    @GetMapping(path = "myPosts")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<PostDTO> getDoctorPosts(){
        // TODO get doctor id
        long doctor_id =1;
        return postService.getDoctorPosts(doctor_id);
    }

    @GetMapping(path = "/{post_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public Map<String,Object> getPostDetails(@PathVariable(name = "post_id") long post_id){
        long doctor_id = 1 ;
        return postService.getPostDetails(post_id,doctor_id);
    }

    @GetMapping(path = "/{post_id}/comments")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<CommentDTO> getPostComments(@PathVariable(name = "post_id") long post_id){
        return postService.getPostComments(post_id);
    }

    @PutMapping(path = "close/{post_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void closePost(@PathVariable(name = "post_id") long post_id){
        long doctor_id = 1 ;
        postService.closePost(post_id,doctor_id);
    }

    @PutMapping(path = "open/{post_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void openPost(@PathVariable(name = "post_id") long post_id){
        long doctor_id = 1 ;
        postService.openPost(post_id,doctor_id);
    }

    @PutMapping(path = "resolved/{post_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void resolvePost(@PathVariable(name = "post_id") long post_id){
        long doctor_id = 1;
        postService.resolvePost(post_id,doctor_id);
    }

    @PostMapping(path = "create/{case_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void createPost(@PathVariable(name = "case_id") long case_id,
                           @RequestBody Map<String,Object> post_info){
        long doctor_id = 1 ;
        if (case_id==0) postService.createPost(doctor_id, post_info);
        else postService.createPostFromCase(case_id, doctor_id, post_info);
    }
}
