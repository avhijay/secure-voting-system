package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Election;
import com.votingSystem.secureVote.repository.ElectionRepository;
import com.votingSystem.secureVote.service.ElectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ElectionServiceImpl implements ElectionService {

    private ElectionRepository electionRepository;

    public ElectionServiceImpl(ElectionRepository electionRepository1){
        this.electionRepository=electionRepository1;
    }


    @Override
    public Election createElection(Election election) {
        return electionRepository.save(election);
    }

    @Override
    public Election getElectionById(Long electionId) {
        return electionRepository.findById(electionId).orElseThrow(()->new RuntimeException("Id not found :"+electionId));
    }

    @Override
    public Election getElectionByName(String name) {
        return electionRepository.findByName(name);
    }

    @Override
    public List<Election> getAllElection() {
        return electionRepository.findAll();
    }

    @Override
    public List<Election> getOnGoingElection() {
        return null;
    }

    @Override
    public List<Election> getUpcomingElection() {
        return null;
    }
@Transactional
    @Override
    public Election updateElectionStatus(Long id, String status) {
        Election theElection = electionRepository.findById(id).orElseThrow(()->new RuntimeException("Id not found  :"+id));
        theElection.setStatus(status);
        return theElection ;
    }

    @Override
    public void closeElection(Long id) {
        Election closeElection = electionRepository.findById(id).orElseThrow(()->new RuntimeException("Incorrect election id :"+id));
        closeElection.setStatus("Closed");


    }
}
