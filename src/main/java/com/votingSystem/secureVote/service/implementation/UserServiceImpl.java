package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.repository.UserRepository;
import com.votingSystem.secureVote.service.UserService;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.votingSystem.secureVote.entity.Users;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository theUserRepository){
        this.userRepository=theUserRepository;
    }




    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->new RuntimeException("User not found with the id: "+ id));
    }

    @Override
    public Users getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<Users> getUserByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<Users> getUserByStatus(String status) {
        return userRepository.findByStatus(status);
    }

    @Override
    public Users createUser(Users user) {
        return userRepository.save(user);
    }




    @Override
    public Users updateUsers(Long id, Users user) {
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }
}
