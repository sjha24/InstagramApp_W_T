package com.saurav.InstagramApp.repository;
import com.saurav.InstagramApp.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByToken(String token);
}
