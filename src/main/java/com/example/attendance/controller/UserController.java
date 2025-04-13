package com.example.attendance.controller;

import com.example.attendance.model.User;
import com.example.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // empty user object for form binding
        return "register"; // register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user); // save to DB
        return "redirect:/login"; // after registration go to login page
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }
}