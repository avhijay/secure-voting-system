package com.votingSystem.secureVote.rest;


import com.votingSystem.secureVote.dto.UserRequest;
import com.votingSystem.secureVote.dto.UserResponse;
import com.votingSystem.secureVote.dto.VoteRequest;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/{status}")
    public  ResponseEntity<List<Users>>searchByStatus(@PathVariable String status){
        List<Users> user = userService.getUserByStatus(status);


        return  ResponseEntity.ok(user);
    }
    @GetMapping("/{role}")
    public  ResponseEntity<List<Users>>getByRole(@PathVariable String role){
        List<Users> user = userService.getUserByStatus(role);


        return  ResponseEntity.ok(user);
    }


}
