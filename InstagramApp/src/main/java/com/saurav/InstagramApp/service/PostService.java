package com.saurav.InstagramApp.service;
import com.saurav.InstagramApp.model.Post;
import com.saurav.InstagramApp.model.User;
import com.saurav.InstagramApp.repository.IAuthenticationRepo;
import com.saurav.InstagramApp.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    @Autowired
    IAuthenticationRepo authenticationRepo;
    public void addPost(Post post) {
        postRepo.save(post);
    }

    public List<Post> getAllPosts(String token) {
        System.out.println("P_service");
        User user = authenticationRepo.findFirstByToken(token).getUser();


        List<Post> postList = postRepo.findByUser(user);

        return postList;


    }
}
