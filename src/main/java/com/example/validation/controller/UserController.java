package com.example.validation.controller;

import com.example.validation.entity.User;
import com.example.validation.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<String> users = List.of("asd", "qwe");

    @GetMapping
    public List<User> getAllUsers() { // TODO page, specification itp..
        return List.of();
    }

    @GetMapping("/{username}")
    public String getUserByUsername(@PathVariable String username) { // TODO page, specification itp..
        if (users.contains(username)) {
            return username;
        } else {
            throw new UserNotFoundException("User " + username + " not found.");
        }
    }

    @PostMapping
    public User addNewUser(@RequestBody @Valid User user) {
        return user;
    }

}
