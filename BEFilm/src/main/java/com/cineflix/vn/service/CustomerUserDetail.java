package com.cineflix.vn.service;

import com.cineflix.vn.model.RoleMapping;
import com.cineflix.vn.model.User;
import com.cineflix.vn.model.dao.RoleMappingDao;
import com.cineflix.vn.model.dao.UserDao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerUserDetail implements UserDetailsService {
    private final UserDao userDAO;
    private final RoleMappingDao roleMappingDAO;

    public CustomerUserDetail(UserDao userDAO, RoleMappingDao roleMappingDAO) {
        this.userDAO = userDAO;
        this.roleMappingDAO = roleMappingDAO;
    }
    @Override
    @Cacheable(value = "userDetailsCache", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User us = userDAO.findByUsername(username);
        if (us == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        List<GrantedAuthority> authorities = roleMappingDAO.findByUserUsername(username)
                .stream()
                .map(roleMapping -> new SimpleGrantedAuthority(roleMapping.getRole().getIdRole()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                us.getUsername(),
                us.getPassword(),
                authorities
        );
    }
}
