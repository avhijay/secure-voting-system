package com.votingSystem.secureVote.security;

import com.votingSystem.secureVote.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements  UserDetails{

    private final Users users;


    public CustomUserDetails(Users user){
        this.users= user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "Role_"+users.getRole().toUpperCase();// spring security roles start with ROLE_
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    public Long getUserId(){
        return users.getId();
    }

    public int getClearanceLevel(){
        return users.getClearanceLevel();
    }

    @Override
    public String getUsername() {
        return users.getIdentityKey(); // CREDENTIAL With which the user logs(can be changed to email / check flyway )
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return users.getStatus().equalsIgnoreCase("ACTIVE"); // checks if user is active or deleyted
// shift to enum
    }
}
