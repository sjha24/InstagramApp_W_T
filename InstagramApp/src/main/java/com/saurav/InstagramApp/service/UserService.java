package com.saurav.InstagramApp.service;
import com.saurav.InstagramApp.dto.SignInInput;
import com.saurav.InstagramApp.dto.SignInOutput;
import com.saurav.InstagramApp.dto.SignUpInput;
import com.saurav.InstagramApp.dto.SignUpOutput;
import com.saurav.InstagramApp.model.AuthenticationToken;
import com.saurav.InstagramApp.model.User;
import com.saurav.InstagramApp.repository.IAuthenticationRepo;
import com.saurav.InstagramApp.repository.IUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IAuthenticationRepo authenticationRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;
    public SignUpOutput signUp(SignUpInput signUpDto) {
        //check if user exists or not based on email
        User user = userRepo.findFirstByEmail(signUpDto.getEmail());

        if(user != null)
        {
            throw new IllegalStateException("Instagram user already exists!!!");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        user = new User(signUpDto.getFirstName(), signUpDto.getLastName(),
                encryptedPassword , signUpDto.getAge() , signUpDto.getEmail(), signUpDto.getPhoneNumber());

        userRepo.save(user);

        return new SignUpOutput("Instagram user registered","Instagram account created successfully");

    }
        public static String encryptPassword(String userPassword)throws NoSuchAlgorithmException{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(userPassword.getBytes());
            byte[] digested = md5.digest();
            return DatatypeConverter.printHexBinary(digested);
        }




    public SignInOutput signIn(SignInInput signInDto) {
        //check if user exists or not based on email
        User user = userRepo.findFirstByEmail(signInDto.getEmail());

        if(user == null)
        {
            throw new IllegalStateException("User invalid !!!");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid !!!");
        }

        AuthenticationToken token = new AuthenticationToken(user);

        AuthenticationTokenService.saveToken(token);

        //set up output response

        return new SignInOutput("Authentication Successfully !!!", token.getToken());

    }


    public void updateUser(User user , String token) {
        User originalUser = authenticationRepo.findFirstByToken(token).getUser();

        if(!(user.getFirstName().isEmpty())){
            originalUser.setFirstName(user.getFirstName());
        }
        if((user.getLastName()!=null)){
            originalUser.setLastName(user.getLastName());
        }
        if((user.getPassword()!=null)){
            String encryptedPassword = null;

            try {
                encryptedPassword = encryptPassword(user.getPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            originalUser.setPassword(encryptedPassword);
        }

        if(user.getAge()!=null){
            originalUser.setAge(user.getAge());
        }

        if((user.getPhoneNumber()!=null)){
            Pattern p = Pattern.compile("\\d{10}");

            Matcher m = p.matcher(user.getPhoneNumber());
            if( (m.find() && m.group().equals(user.getPhoneNumber()))){
                originalUser.setPhoneNumber(user.getPhoneNumber());

            }else{
                throw new IllegalStateException("Enter correct details");
            }

        }

        if((user.getEmail()!=null)){
            Pattern p = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");

            Matcher m = p.matcher(user.getEmail());
            if( (m.find() && m.group().equals(user.getEmail()))){
                originalUser.setEmail(user.getEmail());

            }else{
                throw new IllegalStateException("Enter correct details");
            }
        }

        userRepo.save(originalUser);

    }
}
