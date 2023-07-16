package com.saurav.InstagramApp.repository;

import com.saurav.InstagramApp.model.Post;
import com.saurav.InstagramApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepo  extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
}
