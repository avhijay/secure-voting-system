package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ElectionRepository extends JpaRepository<Election,Long> {

    Election findByName(String name);

    List<Election> findByStatus(String status);

    List<Election> findByCreatedBy(Long userId);

    List<Election> findByStartDateBeforeAndEndDate(Timestamp now);



}
