package com.ht.HabitTracker.Controller;

import com.ht.HabitTracker.Model.User;
import com.ht.HabitTracker.Repository.UserRepository;
import com.ht.HabitTracker.Security.JwtUtils;
import com.ht.HabitTracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // Registration endpoint
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.registerUser(user);
        return "redirect:/login"; // Redirect to login after successful registration
    }

    // Login endpoint (spring security will handle the authentication automatically)
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user != null && new BCryptPasswordEncoder().matches(password, user.get().getPassword())) {
            return "redirect:/home";  // Redirect to the home page after successful login
        }
        return "redirect:/login?error";  // Show error if login fails
    }
}

