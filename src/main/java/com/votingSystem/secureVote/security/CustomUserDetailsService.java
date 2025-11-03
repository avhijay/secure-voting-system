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
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (userRepository.findByIdentityKey(username) == null) {
            throw new UserNotFound("no User found : " + username);
        } else {

            Users user = userRepository.findByIdentityKey(username);




            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getName())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .disabled(!"ACTIVE".equalsIgnoreCase(user.getStatus()))
                    .build();


        }
    }
}