package com.votingSystem.secureVote.security;


import com.votingSystem.secureVote.dto.UserResponse;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.exception.ResourceNotFoundException;
import com.votingSystem.secureVote.exception.UserNotFound;
import com.votingSystem.secureVote.repository.UserRepository;
import com.votingSystem.secureVote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService { // spring security built-in interface

    private final UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    // username spring thinks the user is logging from
    public UserDetails loadUserByUsername(String identityKey) throws UsernameNotFoundException {
        Users user = userRepository.findByIdentityKey(identityKey).orElseThrow(()->new UserNotFound("User not found   with identity key :"+identityKey));



        return  new CustomUserDetails(user); // wrapping users entity to security format
    }
}
