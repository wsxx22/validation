package com.example.validation.controller;

import com.example.validation.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers() { // TODO page, specification itp..
        return List.of();
    }

    @PostMapping
    public User addNewUser(@RequestBody @Valid User user) {
        return user;
    }

}
