package com.joshua.application.service;

import com.joshua.application.dto.AddressDTO;
import com.joshua.application.dto.UserRequest;
import com.joshua.application.dto.UserResponse;
import com.joshua.application.model.Address;
import com.joshua.application.model.User;
import com.joshua.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private List<User> userList = new ArrayList<>();
//    private Long nextId = 1L;
    public List<UserResponse> fetchAllUsers(){
        return userRepository.findAll().stream()
        .map(this::mapToUserResponse)
        .collect(Collectors.toList());
    }
    public void addUser(UserRequest userRequest){
        User user = new User();
        updateUserFromRequest(userRequest, user);
        userRepository.save(user);

    }

    public Optional<UserResponse> fetchUser(Long id){
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }

    private UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());

        if (user.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }
        return response;
    }
    private void updateUserFromRequest(UserRequest userRequest, User user){
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if (userRequest.getAddress() != null){
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(address);
        }
    }
    
}
