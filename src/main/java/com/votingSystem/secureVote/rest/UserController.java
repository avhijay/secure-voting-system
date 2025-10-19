package com.votingSystem.secureVote.rest;


import com.votingSystem.secureVote.dto.UserRequest;
import com.votingSystem.secureVote.dto.UserResponse;
import com.votingSystem.secureVote.dto.VoteRequest;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController (UserService userService1 ){
        this.userService= userService1;
    }

    @PostMapping
    public  ResponseEntity<UserResponse>createUser(@Valid @RequestBody UserRequest userRequest){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Users newUser = new Users(userRequest.getName(),userRequest.getIdentityKey(),userRequest.getEmailId(),userRequest.getPassword(),userRequest.getRole(),userRequest.getStatus(),userRequest.getClearanceLevel(),now,now);
        Users save = userService.createUser(newUser);
        UserResponse response= new UserResponse();
        response.setName(save.getName());
        response.setClearanceLevel(save.getClearanceLevel());
        response.setStatus(save.getStatus());
        response.setRole(save.getRole());
        response.setEmailId(save.getEmail());


        URI location = URI.create("/api/users"+newUser.getId());
        return ResponseEntity.created(location).body(response);

    }
}
