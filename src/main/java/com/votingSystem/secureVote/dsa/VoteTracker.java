package com.votingSystem.secureVote.dsa;


import com.votingSystem.secureVote.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VoteTracker {

    private final ConcurrentHashMap<Long , Set<Long>> totalVoting  = new ConcurrentHashMap<>();

    public VoteTracker (){}

    public boolean hasVoted(Long electionId ,Long userId){
        Set<Long> votes = totalVoting.get(electionId);
        if (votes==null){
            throw new ResourceNotFoundException("No election found by the id ");
        }else{
            return votes.contains(userId);
        }

    }

    public void casting(Long electionId, Long userId){

        Set<Long>votes = totalVoting.computeIfAbsent(electionId,id->ConcurrentHashMap.newKeySet());
        votes.add(userId);

    }
public void clearElection(Long electionId){
        totalVoting.remove(electionId);
}

public void clearAllElectionsData(){
        totalVoting.clear();
}








}
