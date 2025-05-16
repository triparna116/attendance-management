package com.example.attendance.service;

import com.example.attendance.controller.exception.AuthenticationFailureException;
import com.example.attendance.model.User;
import com.example.attendance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

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
    // Encode password before saving
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    System.out.println("Saved user: " + user.getUsername() + " with encoded password: " + user.getPassword());
} // <-- This closing brace is VERY IMPORTANT

public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElse(null);
}

       public User login(String userName, String password) throws AuthenticationFailureException {
        User user = findByUsername(userName);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("username", userName);
            return user;
        } else {
            throw new AuthenticationFailureException();
        }
    }
}


