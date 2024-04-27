package com.auth1.auth.learning.controller;

import com.auth1.auth.learning.dtos.EmailDTOSD;
import com.auth1.auth.learning.dtos.LoginRequestDto;
import com.auth1.auth.learning.dtos.SendEmailDTO;
import com.auth1.auth.learning.dtos.SignupRequestDto;
import com.auth1.auth.learning.model.Token;
import com.auth1.auth.learning.model.User;
import com.auth1.auth.learning.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/findUser")
    public Optional<User> findUserEmail(@RequestBody EmailDTOSD emailDTOSD){

        return userService.findByEmail(emailDTOSD.getEmail());


    }

    @PostMapping("/signup")
    public User signUp(@RequestBody SignupRequestDto requestDto) throws JsonProcessingException, ExecutionException, InterruptedException {
        return userService.signUp(requestDto.getEmail(),
                requestDto.getPassword(), requestDto.getName());
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto){
        return userService.login(requestDto.getEmail(),
                requestDto.getPassword());
    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<Void> logout(@PathVariable("token") String token){
        userService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("validateToken/{token}")
    public boolean validate(@PathVariable("token") String token){
        return userService.validateToken(token);
    }
}
