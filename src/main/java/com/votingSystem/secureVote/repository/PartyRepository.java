package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.Parties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Parties,Long> {

    Parties findByName(String name);

    Parties findBySymbol(String symbol);

    List<Parties>findAllByFoundedYearGreaterThan(int year );
}
