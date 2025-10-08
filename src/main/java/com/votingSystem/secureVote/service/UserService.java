package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.Users;


import java.util.List;

public interface UserService {


    List<Users> getAllUsers();
    Users getUserById(Long id);
    Users getUserByIdentityKey(String identityKey);
    List<Users> getUserByRole(String role);
    List<Users> getUserByStatus(String status);
    Users createUser(Users user);
    Users updateUsers(Long id, Users user);
    void delete(Long id);
}
