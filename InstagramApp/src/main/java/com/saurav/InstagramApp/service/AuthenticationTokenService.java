package com.saurav.InstagramApp.service;
import com.saurav.InstagramApp.model.AuthenticationToken;
import com.saurav.InstagramApp.repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {

    @Autowired
    static

    IAuthenticationRepo authenticationRepo;
    public static void saveToken(AuthenticationToken token) {
        authenticationRepo.save(token);
    }

    public boolean authenticate(String email, String token) {
        if(token==null && email==null){
            return false;
        }

        AuthenticationToken authToken = authenticationRepo.findFirstByToken(token);

        if(authToken==null){
            return false;
        }

        String expectedEmail = authToken.getUser().getEmail();

        return expectedEmail.equals(email);
    }


    public void deleteToken(String token) {
        AuthenticationToken token1 = authenticationRepo.findFirstByToken(token);

        authenticationRepo.deleteById(token1.getTokenId());
    }
}