package com.cineflix.vn.model.dao;

import com.cineflix.vn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

}
