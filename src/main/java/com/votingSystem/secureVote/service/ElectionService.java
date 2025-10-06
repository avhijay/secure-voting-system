package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.Election;

import java.util.List;

public interface ElectionService {

    Election createElection(Election election);

    Election getElectionById(Long electionId);

    Election getElectionByName(String name);

    List<Election> getAllElection();

    List<Election> getOnGoingElection();

    List<Election> getUpcomingElection();

    Election updateElectionStatus(Long id , String status);

    void closeElection(Long id);

}
