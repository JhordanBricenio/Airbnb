package com.codej.service.impl;

import com.codej.exception.UserException;
import com.codej.model.User;
import com.codej.repository.IUserRepository;
import com.codej.response.AuthResponse;
import com.codej.security.JwtProvider;
import com.codej.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

    private JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse save(User user) {
        User isExistEmail= userRepository.findByEmail(user.getEmail());
        if(isExistEmail!=null){
            throw new UserException("Email is already exist with email: "+user.getEmail());

        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.generateToken(authentication);
        AuthResponse authResponse= new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setStatus(true);
        authResponse.setMessage("User created successfully");

        return authResponse;
    }

    @Override
    public User findByEmail(String email) {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw  new UserException("User not found");
        }
        return user;
    }

    @Override
    public User  findUserProfileByJwt(String jwt) {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw  new UserException("User not found");
        }
        return user;
    }

    @Override
    public User finUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }



    @Override
    public void deleteUser(Long id) {
            userRepository.deleteById(id);
    }
}
