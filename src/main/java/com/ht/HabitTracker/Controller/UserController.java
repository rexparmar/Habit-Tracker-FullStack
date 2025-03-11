package com.ht.HabitTracker.Controller;

import com.ht.HabitTracker.Model.User;
import com.ht.HabitTracker.Repository.UserRepository;
import com.ht.HabitTracker.Service.UserService;
import jdk.javadoc.doclet.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        Optional<User> existingUser = repo.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            return "User already exists with this email!";
        }
        service.registerUser(user);
        return "User registered successfully!";
    }

    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) throws Exception{
        Optional<User> existingUser = repo.findByEmail(email);
        if(existingUser.isPresent()){
            return existingUser;
        }
         throw new Exception("User does not exist with this email!");
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users = repo.findAll();
        return users;
    }

    @GetMapping("/userData")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity.ok("This is data for users only!");
    }
}
