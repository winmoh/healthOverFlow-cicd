package com.example.MedInsightHub.like;

import com.example.MedInsightHub.user.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final DoctorService doctorService;

    @PostMapping(path = "/post/{post_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void likePost(@PathVariable(name = "post_id") long post_id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            likeService.likePost(post_id,doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @PostMapping(path = "/comment/{comment_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void likeComment(@PathVariable(name = "comment_id") long comment_id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            likeService.likeComment(comment_id,doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }

    @DeleteMapping(path = "delete/{like_id}")
    @PreAuthorize("hasAuthority('Doctor')")
    public void deleteLike(@PathVariable(name = "like_id") long like_id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ( principal instanceof UserDetails) {
            long doctor_id = doctorService.getDoctorByUsername(((UserDetails) principal).getUsername()).getDoctor_id();
            likeService.deleteLike(like_id, doctor_id);
        } else {
            throw new UsernameNotFoundException("doctor not found!!!");
        }
    }
}
