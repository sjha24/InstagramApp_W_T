package com.saurav.InstagramApp.controller;
import com.saurav.InstagramApp.dto.SignInInput;
import com.saurav.InstagramApp.dto.SignInOutput;
import com.saurav.InstagramApp.dto.SignUpInput;
import com.saurav.InstagramApp.dto.SignUpOutput;
import com.saurav.InstagramApp.model.User;
import com.saurav.InstagramApp.service.AuthenticationTokenService;
import com.saurav.InstagramApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationTokenService authService;

    @PostMapping("/signup")
    public SignUpOutput signUp(@Valid @RequestBody SignUpInput signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@Valid @RequestBody SignInInput signInDto){
        return userService.signIn(signInDto);
    }

    @DeleteMapping("/signout")
    public ResponseEntity<String> signOut(@RequestParam String email , @RequestParam String token){
        HttpStatus status;
        String message=null;

        if(authService.authenticate(email,token))
        {
            authService.deleteToken(token);
            message = "Signout Successful";
            status = HttpStatus.OK;

        }
        else
        {
            message = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(message , status);
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestParam String email , @RequestParam String token , @RequestBody User user){
        HttpStatus status;
        String msg=null;

        if(authService.authenticate(email,token))
        {
            try{
                userService.updateUser(user , token);
                status = HttpStatus.OK;
                msg = "User updated sucessfully";
            }catch (Exception e){
                msg = "Enter valid information";
                status = HttpStatus.BAD_REQUEST;
            }

        }
        else
        {
            msg = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

}
