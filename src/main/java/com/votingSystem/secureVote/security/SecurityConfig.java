package com.votingSystem.secureVote.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService){
        this.userDetailsService=customUserDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    // access to HttpSecurity contextt
    public AuthenticationManager authenticationManager (HttpSecurity http)throws Exception{
        AuthenticationManagerBuilder authenticationBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);  // get shared object spring creates one amb builder and every where we retrieve the same.userDetailsService(AuthenticationManagerBuilder.class

        authenticationBuilder
                .userDetailsService(userDetailsService) // using userDetailService to fetch user
                .passwordEncoder(passwordEncoder()); // compare password
        return authenticationBuilder.build();




    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth ->auth

                        .requestMatchers("/h2-console/**").permitAll()
                        // access by all endPoints ->

                        .requestMatchers("/api/auth/**").permitAll()

                        .requestMatchers("/api/candidates/search/*").hasAnyRole("VOTER","ADMIN","CANDIDATE")
                        .requestMatchers("/api/candidates/**").hasRole("ADMIN")

                        .requestMatchers("/api/candidates/reject/*/by/*").hasRole("ADMIN")
                        .requestMatchers("/api/candidates/approve/*/by/*").hasRole("ADMIN")


                        //audits
                        .requestMatchers("/api/audits/**").hasRole("ADMIN")

                        //election

                        .requestMatchers("/api/elections/**").hasRole("ADMIN")


                        //VOTE
                        .requestMatchers("/api/votes/**").hasRole("ADMIN")
                        .requestMatchers("/api/votes/cast").hasAnyRole("VOTER","ADMIN","CANDIDATE")



                        //USERS

                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        .anyRequest().authenticated()







                ).httpBasic(Customizer.withDefaults());


return http.build();
    }







}
