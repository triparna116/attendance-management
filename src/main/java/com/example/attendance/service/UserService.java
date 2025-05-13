package com.example.attendance.service;

import com.example.attendance.controller.exception.AuthenticationFailureException;
import com.example.attendance.model.User;
import com.example.attendance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    public void saveUser(User user) {
        userRepository.save(user);
        System.out.println("user: " + user.getUsername() + " pass: " + user.getPassword() + " role: " + user.getRole());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User login(String userName, String password) throws AuthenticationFailureException {
        System.out.println("user: " + userName);
        User user = findByUsername(userName);
        System.out.println("user: " + user.getUsername() + " pass: " + user.getPassword() + " role: " + user.getRole());
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new AuthenticationFailureException();
        }
    }
}
