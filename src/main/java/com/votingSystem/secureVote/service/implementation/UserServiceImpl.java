package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.dto.UserRequest;
import com.votingSystem.secureVote.dto.UserResponse;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.exception.UserNotFound;
import com.votingSystem.secureVote.repository.UserRepository;
import com.votingSystem.secureVote.security.AuthContext;
import com.votingSystem.secureVote.service.AuditService;
import com.votingSystem.secureVote.service.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.votingSystem.secureVote.entity.Users;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditService auditService;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository theUserRepository , PasswordEncoder passwordEncoder , AuditService auditService){

        this.userRepository=theUserRepository;
        this.passwordEncoder=passwordEncoder;
        this.auditService=auditService;
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
    public Users getUserByIdentityKey(String identityKey) {
        return userRepository.findByIdentityKey(identityKey).orElseThrow(()->new UserNotFound("User Not found with identity key :"+identityKey));
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
    public UserResponse createUser(UserRequest userRequest) {

        Long sessionUserId = AuthContext.userId();
        log.info("User {} attempting to create a new user",sessionUserId);

        Users user = new Users();
        user.setEmail(userRequest.getEmailId());
        user.setIdentityKey(userRequest.getIdentityKey());
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole());
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setStatus(userRequest.getStatus());
        user.setClearanceLevel(userRequest.getClearanceLevel());
        Users saved = userRepository.save(user);
        if (saved.getRole().equalsIgnoreCase("ADMIN")) {
            log.warn("User {} creating new user with ADMIN role", sessionUserId);
        }
            log.debug("User {} successfully created a user with id {}", sessionUserId, saved.getId());
            auditService.logAction(sessionUserId, "CREATE_USER", "SUCCESS", null);


            return new UserResponse(saved);

    }


    // MAKE IT CUSTOM





    @Override
    public Users updateUsers(Long id, Users user) {
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }
}
