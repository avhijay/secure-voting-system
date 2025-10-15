package com.votingSystem.secureVote.rest;

import com.votingSystem.secureVote.dto.VoteRequest;
import com.votingSystem.secureVote.dto.VoteResponse;
import com.votingSystem.secureVote.entity.Votes;
import com.votingSystem.secureVote.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

        URI  location = URI.create("/api/votes/"+data.getVoteId());
        return ResponseEntity.created(location).body(data);
    }





}


