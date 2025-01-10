package com.joshua.application.controller;

import com.joshua.application.model.User;
import com.joshua.application.service.UserService;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user){userService.addUser(user);
      return ResponseEntity.ok("User added successfully");

    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.fetchUser(id);

        if (user == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}
