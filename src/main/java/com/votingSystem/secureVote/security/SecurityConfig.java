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
    public AuthenticationManager authenticationManager(HttpSecurity http)throws Exception{
        AuthenticationManagerBuilder  amb=http.getSharedObject(AuthenticationManagerBuilder.class);
        amb.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return amb.build();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(configure->configure

                        .requestMatchers("/h2-console/**").permitAll()
                        // access by all endPoints ->

                        .requestMatchers("/api/auth/**").permitAll()

                        .requestMatchers("/api/candidates/search/{name}").hasAnyRole("VOTER","ADMIN","CANDIDATE")
                        .requestMatchers("/api/candidates/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/candidates/election/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/candidates/reject/{id}/by/{userId}").hasRole("ADMIN")
                        .requestMatchers("/api/candidates/approve/{id}/by/{userId}").hasRole("ADMIN")
                        .requestMatchers("/api/candidates").hasRole("ADMIN")

                        //audits
                        .requestMatchers("/api/audits/**").hasRole("ADMIN")

                        //election

                        .requestMatchers("/api/elections/**").hasRole("ADMIN")
                        .requestMatchers("/api/elections/{name}").hasRole("VOTER")

                        //VOTE
                        .requestMatchers("/api/votes/**").hasRole("ADMIN")
                        .requestMatchers("/api/votes/cast").hasAnyRole("VOTER","ADMIN","CANDIDATE")



                        //USERS

                        .requestMatchers("/api/users/**").permitAll()

                        .anyRequest().authenticated()







                ).httpBasic(Customizer.withDefaults());

return http.build();
    }







}
