package com.joshua.application.controller;

import com.joshua.application.dto.UserResponse;
import com.joshua.application.dto.UserRequest;
import com.joshua.application.model.User;
import com.joshua.application.service.UserService;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){userService.addUser(userRequest);
      return ResponseEntity.ok("User added successfully");

    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
       Optional<UserResponse> user = userService.fetchUser(id);

        if (user == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}
