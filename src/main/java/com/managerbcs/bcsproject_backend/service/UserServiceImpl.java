package com.managerbcs.bcsproject_backend.service;

import com.managerbcs.bcsproject_backend.dao.UserRepository;
import com.managerbcs.bcsproject_backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getStudentId(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRole()) // nếu dùng 1 role dạng Enum
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(User.Role role) {
        return java.util.List.of(new SimpleGrantedAuthority(role.name()));
    }
}
