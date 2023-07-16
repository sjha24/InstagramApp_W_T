package com.saurav.InstagramApp.controller;
import com.saurav.InstagramApp.model.Post;
import com.saurav.InstagramApp.service.AuthenticationTokenService;
import com.saurav.InstagramApp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    AuthenticationTokenService authService;
    @PostMapping()
    public String addPost(@Valid @RequestParam String email , @RequestParam String token , @RequestBody Post post){
        String message = "";
        if(authService.authenticate(email,token))
        {
            postService.addPost(post);
            message = " Post posted successfully";
        }
        else
        {
            message = "Invalid user";
        }

        return message;
    }

    @GetMapping()
    public List<Post> getAllPosts(@RequestParam String email , @RequestParam String token){
        List<Post> postList = null;
        if(authService.authenticate(email,token)) {
            postList = postService.getAllPosts(token);
        }

        return postList;
    }
}
