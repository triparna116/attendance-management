package com.example.attendance.controller;

import com.example.attendance.controller.exception.AuthenticationFailureException;
import com.example.attendance.model.User;
import com.example.attendance.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/auth/login")
    public String loginPage() {
        return "login"; // maps to login.html
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            User user = userService.login(username, password);
        } catch (AuthenticationFailureException e) {
            e.printStackTrace();
            return "login?error=true";
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "login?error=true";
        }
        return "dashboard";
    }

    @GetMapping("/user/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // make sure User is properly imported
        return "register"; // maps to register.html
    }

    @PostMapping("/user/register")
    public String register(User user) {
        try {
            user.setRole("ROLE_USER");
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "register?error=true";
        }
        return "login";
    }
}
