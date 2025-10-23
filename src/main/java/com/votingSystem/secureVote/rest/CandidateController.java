package com.votingSystem.secureVote.rest;

import com.votingSystem.secureVote.dto.CandidateRequest;
import com.votingSystem.secureVote.dto.CandidateResponse;
import com.votingSystem.secureVote.entity.Candidates;
import com.votingSystem.secureVote.entity.Election;
import com.votingSystem.secureVote.entity.Parties;
import com.votingSystem.secureVote.service.CandidateService;
import com.votingSystem.secureVote.service.ElectionService;
import com.votingSystem.secureVote.service.PartiesService;
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
@RequestMapping("/api/candidates")
public class CandidateController {


    private final  CandidateService candidateService;
    private final PartiesService partiesService;
    private final ElectionService electionService;
    public  CandidateController (CandidateService candidateService1 , PartiesService partiesService1 , ElectionService  electionService){
        this.candidateService=candidateService1;
        this.partiesService=partiesService1;
        this.electionService=electionService;

    }

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(@Valid @RequestBody CandidateRequest request  ){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Candidates candidate = new Candidates();
        candidate.setName(request.getName());
        Parties party = partiesService.findByName(request.getName());
        if (party!=null){
            candidate.setParty(party);
        }else{
            candidate.setParty(null);
        }
        candidate.setCreatedAt(now);
        candidate.setUpdatedAt(now);
        candidate.setBio(request.getBio());
        Election election = electionService.getElectionById(request.getElectionId());
        candidate.setElection(election);

        candidateService.createCandidate(candidate);
        CandidateResponse response = new CandidateResponse();
        response.setBio(candidate.getBio());
        response.setElectionId(candidate.getElection().getId());
        response.setCreatedAt(candidate.getCreatedAt());
        response.setId(candidate.getId());


        URI location = URI.create("/api/candidates"+candidate.getId());
        return ResponseEntity.created(location).body(response);
    }


}
