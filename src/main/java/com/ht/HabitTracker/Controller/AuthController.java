package com.ht.HabitTracker.Controller;

import com.ht.HabitTracker.Model.User;
import com.ht.HabitTracker.Repository.UserRepository;
import com.ht.HabitTracker.Security.JwtUtils;
import com.ht.HabitTracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return "User already exists!";
        }
        userService.registerUser(user);
        return "User has been successfully registered in system!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user != null && user.isPresent() && new BCryptPasswordEncoder().matches(password,user.get().getPassword())) {
            return "Login successful!";
        }
        return "redirect:/login?error";
    }
}

