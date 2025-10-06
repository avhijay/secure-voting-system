package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Election;
import com.votingSystem.secureVote.service.ElectionService;

import java.util.List;

public class ElectionServiceImpl implements ElectionService {
    @Override
    public Election createElection(Election election) {
        return null;
    }

    @Override
    public Election getElectionById(Long electionId) {
        return null;
    }

    @Override
    public Election getElectionByName(String name) {
        return null;
    }

    @Override
    public List<Election> getAllElection() {
        return List.of();
    }

    @Override
    public List<Election> getOnGoingElection() {
        return List.of();
    }

    @Override
    public List<Election> getUpcomingElection() {
        return List.of();
    }

    @Override
    public Election updateElectionStatus(Long id, String status) {
        return null;
    }

    @Override
    public void closeElection(Long id) {

    }
}
