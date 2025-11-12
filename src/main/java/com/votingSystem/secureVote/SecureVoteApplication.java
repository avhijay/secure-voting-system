package com.votingSystem.secureVote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SecureVoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureVoteApplication.class, args);
	}

}
