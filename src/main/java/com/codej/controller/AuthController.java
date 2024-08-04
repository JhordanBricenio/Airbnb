package com.codej.controller;

import com.codej.model.User;
import com.codej.request.AuthLogin;
import com.codej.response.AuthResponse;
import com.codej.service.IUserService;
import com.codej.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private IUserService userService;

    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user){
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLogin userRequest) {
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }



}
