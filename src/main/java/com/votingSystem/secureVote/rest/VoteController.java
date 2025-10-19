package com.votingSystem.secureVote.rest;

import com.votingSystem.secureVote.dto.VoteRequest;
import com.votingSystem.secureVote.dto.VoteResponse;
import com.votingSystem.secureVote.entity.Votes;
import com.votingSystem.secureVote.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService1){
        this.voteService=voteService1;

    }

    @PostMapping("/cast")
    public ResponseEntity<VoteResponse> castVote(@Valid @RequestBody VoteRequest request){
        Votes saved = voteService.castVote(request.getVoterId(), request.getElectionId(), request.getCandidateId());

        VoteResponse data = new VoteResponse();
        data.setVoteId(saved.getId());
        data.setElectionId(saved.getElection().getId());

        data.setVoterId(saved.getUsers().getId());
        data.setCandidateId(saved.getCandidates().getId());
        data.setCastAt(Timestamp.valueOf(LocalDateTime.now()).toString());

        URI  location = URI.create("/api/votes/cast"+data.getVoteId());
        return ResponseEntity.created(location).body(data);
    }
    @GetMapping("/election/{id}")
    public ResponseEntity<List<Votes>> returnAllVotes(@PathVariable Long id){
        List<Votes> votes = voteService.returnAllVotesByElectionId(id);
        return ResponseEntity.ok(votes);

    }

    @GetMapping("/count/candidates/{id}")
    public ResponseEntity<Long> countByCandidate(@PathVariable Long id){
        Long count = voteService.countVotesForCandidate(id);
        return ResponseEntity.ok(count);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Votes>getByVoterId(@PathVariable Long id){
        Votes vote = voteService.getVoteByVoteId(id);
        return ResponseEntity.ok(vote);
    }

    @GetMapping("/election{electionId}/voter{voterId}")
    public ResponseEntity<Votes> getByElectionAndUserId(@PathVariable Long electionId
            ,@PathVariable Long voterId){
        Votes vote = voteService.getElectionVotesByVoterId(electionId,voterId);
        return ResponseEntity.ok(vote);

    }



}


