package com.example.MedInsightHub.post;

import com.example.MedInsightHub.comment.CommentDTO;
import com.example.MedInsightHub.user.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/post")
public class PostController {
    private final PostService postService;
    private final DoctorService doctorService;

    @GetMapping(path = "all")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<PostDTO> getNotClosedPosts(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            return postService.getNotClosedPosts(doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @GetMapping(path = "open")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<PostDTO> getOpenPosts(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            return postService.getOpenPosts(doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @GetMapping(path = "resolved")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<PostDTO> getResolvedPosts(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            return postService.getResolvedPosts(doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @GetMapping(path = "myPosts")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<PostDTO> getDoctorPosts(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            return postService.getDoctorPosts(doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @GetMapping(path = "/{post_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public Map<String,Object> getPostDetails(@PathVariable(name = "post_id") long post_id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            return postService.getPostDetails(post_id,doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @GetMapping(path = "/{post_id}/comments")
    @PreAuthorize("hasAuthority('Doctor')")
    public List<CommentDTO> getPostComments(@PathVariable(name = "post_id") long post_id){
        return postService.getPostComments(post_id);
    }

    @PutMapping(path = "close/{post_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void closePost(@PathVariable(name = "post_id") long post_id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            postService.closePost(post_id,doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @PutMapping(path = "open/{post_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void openPost(@PathVariable(name = "post_id") long post_id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            postService.openPost(post_id,doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @PutMapping(path = "resolved/{post_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void resolvePost(@PathVariable(name = "post_id") long post_id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            postService.resolvePost(post_id,doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @PostMapping(path = "create/{case_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void createPost(@PathVariable(name = "case_id") long case_id,
                           @RequestBody Map<String,Object> post_info){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            if (case_id==0) postService.createPost(doctor_id, post_info);
            else postService.createPostFromCase(case_id, doctor_id, post_info);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }
}
