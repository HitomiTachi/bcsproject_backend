package com.managerbcs.bcsproject_backend.service;

import com.managerbcs.bcsproject_backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}
