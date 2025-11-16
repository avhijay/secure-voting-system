package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.dto.UserRequest;
import com.votingSystem.secureVote.dto.UserResponse;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.exception.AccessLevelNotSufficient;
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
    public List<UserResponse> getAllUsers() {
        Long sessionId = AuthContext.userId();
        log.info("ACTION=GET_ALL_USERS | BY_USER={} ",sessionId);
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .toList();
    }



    @Override
    public UserResponse createUser(UserRequest userRequest) {

        Long sessionUserId = AuthContext.userId();
        Users postingUser = userRepository.findById(sessionUserId).orElseThrow(()-> new UserNotFound("No user found with id :"+sessionUserId));
        log.debug("Validating clearance level {} for user {}",postingUser.getClearanceLevel(),postingUser.getId());
        if (postingUser.getClearanceLevel()<2){

            log.warn("USER={} attempted to create user with insufficient clearance level",sessionUserId);
            throw new AccessLevelNotSufficient("user clearanceLevel not sufficient :"+sessionUserId);


        }
        log.info("ACTION=CREATE_USER | BY_USER={}| STATUS=START",sessionUserId);
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
        log.info("ACTION=CREATE_USER | BY_USER={} | STATUS=SUCCESS | NEW_USER_ID={}",sessionUserId,saved.getId());
        auditService.logAction(sessionUserId, "CREATE_USER", "SUCCESS", null);


        return new UserResponse(saved);

    }










    @Override
    public Users getUserById(Long id) {
        Long sessionId = AuthContext.userId();
        return userRepository.findById(id).orElseThrow(() ->new RuntimeException("User not found with the id: "+ id));
    }

    @Override
    public Users getUserByIdentityKey(String identityKey) {
        Long sessionId = AuthContext.userId();
        return userRepository.findByIdentityKey(identityKey).orElseThrow(()->new UserNotFound("User Not found with identity key :"+identityKey));
    }

    @Override
    public List<UserResponse> getUserByRole(String role) {
        Long sessionId = AuthContext.userId();
        return userRepository.findByRole(role).stream().map(UserResponse::new).toList();
    }

    @Override
    public List<UserResponse> getUserByStatus(String status) {
        Long sessionId = AuthContext.userId();
        return userRepository.findByStatus(status)
                .stream()
                .map(UserResponse::new)
                .toList()

                ;
    }



    // MAKE IT CUSTOM





    @Override
    public Users updateUsers(Long id, Users user) {
        Long sessionId = AuthContext.userId();
        return user;
    }

    @Override
    public void delete(Long id) {
        Long sessionId = AuthContext.userId();
        userRepository.deleteById(id);

    }
}
