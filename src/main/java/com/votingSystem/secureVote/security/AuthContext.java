package com.votingSystem.secureVote.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthContext {


    public static CustomUserDetails details(){

        return (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();




    }


    public static Long userId(){
        return details().getUserId();

    }

    public static int clearanceLevel(){
        return details().getClearanceLevel();
    }



}
