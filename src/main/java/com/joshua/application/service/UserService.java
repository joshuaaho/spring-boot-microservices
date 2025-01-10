package com.joshua.application.service;

import com.joshua.application.model.User;
import com.joshua.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private List<User> userList = new ArrayList<>();
//    private Long nextId = 1L;
    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);

    }

    public Optional<User> fetchUser(Long id){
        return userRepository.findById(id);
    }
}
