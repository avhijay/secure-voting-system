package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByIdentityKey(String identityKey);

    Users findByEmail (String email);

    List <Users> findByRole(String role);

    List<Users> findByStatus(String status);

    List<Users> findByClearanceLevel(int level);




}
