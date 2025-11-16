package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.dto.UserRequest;
import com.votingSystem.secureVote.dto.UserResponse;
import com.votingSystem.secureVote.entity.Users;


import java.util.List;
import java.util.Optional;

public interface UserService {


    List<UserResponse> getAllUsers();
    Users getUserById(Long id);
  Users getUserByIdentityKey(String identityKey);
    List<UserResponse> getUserByRole(String role);
    List<UserResponse> getUserByStatus(String status);


    UserResponse createUser(UserRequest userRequest);

    Users updateUsers(Long id, Users user);
    void delete(Long id);
}
