package com.codej.repository;

import com.codej.model.Property;
import com.codej.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByUsername(String username);

}
