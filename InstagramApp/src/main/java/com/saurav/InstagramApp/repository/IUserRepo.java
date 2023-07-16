package com.saurav.InstagramApp.repository;

import com.saurav.InstagramApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);
}
