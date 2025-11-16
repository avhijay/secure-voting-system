package com.votingSystem.secureVote.rest;


import com.votingSystem.secureVote.dto.UserRequest;
import com.votingSystem.secureVote.dto.UserResponse;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController (UserService userService1 ){
        this.userService= userService1;
    }

    @PostMapping("/create")
    public  ResponseEntity<UserResponse>createUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse response = userService.createUser(userRequest);


        URI location = URI.create("/api/users"+response.getId());
        return ResponseEntity.created(location).body(response);

    }

    @GetMapping("/{id}")
    public  ResponseEntity<UserResponse>searchById(@PathVariable Long id){
        Users user = userService.getUserById(id);
        UserResponse newResponse = new UserResponse();
        newResponse.setEmailId(user.getEmail());
        newResponse.setRole(user.getRole());
        newResponse.setStatus(user.getStatus());
        newResponse.setClearanceLevel(user.getClearanceLevel());
        newResponse.setName(user.getName());

        return  ResponseEntity.ok(newResponse);
    }


    @GetMapping("/key/{id}")
    public  ResponseEntity<UserResponse>searchByIdentityKey(@PathVariable String id){
        Users user = userService.getUserByIdentityKey(id);
        UserResponse newResponse = new UserResponse();
        newResponse.setEmailId(user.getEmail());
        newResponse.setRole(user.getRole());
        newResponse.setStatus(user.getStatus());
        newResponse.setClearanceLevel(user.getClearanceLevel());
        newResponse.setName(user.getName());

        return  ResponseEntity.ok(newResponse);
    }

    @GetMapping("/status/{status}")
    public  ResponseEntity<List<UserResponse>>searchByStatus(@PathVariable String status){
        return ResponseEntity.ok(userService.getUserByStatus(status));
    }
    @GetMapping("/role/{role}")
    public  ResponseEntity<List<UserResponse>>getByRole(@PathVariable String role){
     return ResponseEntity.ok(userService.getUserByRole(role));



    }


}
