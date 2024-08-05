package com.codej.service;

import com.codej.model.Property;
import com.codej.model.User;
import com.codej.response.AuthResponse;

import java.util.List;

public interface IUserService {
    public AuthResponse save (User user);
    public User findByEmail(String email);
    public User findUserProfileByJwt(String jwt);
    public User finUserById(Long userId);
    public void deleteUser(Long id);
}
