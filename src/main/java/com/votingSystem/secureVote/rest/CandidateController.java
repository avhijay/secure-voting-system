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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
        candidate.setStatus("Pending");
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

    @PatchMapping("/approve/{id}/by/{userId}")
    public  ResponseEntity<CandidateResponse>approveCandidate(@PathVariable Long id,@PathVariable Long userId, @RequestParam Long ApprovedByUserId){
         Candidates candidates = candidateService.approveCandidate(id,ApprovedByUserId);
         CandidateResponse response = new CandidateResponse();
         response.setName(candidates.getName());
         response.setCreatedAt(candidates.getCreatedAt());
         response.setUpdatedAt(candidates.getUpdatedAt());
         response.setId(candidates.getId());
         response.setApprovedBy(candidates.getApprovedBy());
         response.setStatus(candidates.getStatus());
         return  ResponseEntity.ok(response);


    }

    @PatchMapping("/reject/{id}/by/{userId}")
    public  ResponseEntity<CandidateResponse>rejectCandidate(@PathVariable Long id,@PathVariable Long userId){
        Candidates candidates = candidateService.rejectCandidate(id);
        CandidateResponse response = new CandidateResponse();
        response.setName(candidates.getName());
        response.setCreatedAt(candidates.getCreatedAt());
        response.setUpdatedAt(candidates.getUpdatedAt());
        response.setId(candidates.getId());
        response.setApprovedBy(candidates.getApprovedBy());
        response.setStatus(candidates.getStatus());
        return  ResponseEntity.ok(response);


    }

    @GetMapping("/election/{id}")
    public  ResponseEntity<List< Candidates>>getByElection(@PathVariable Long id){
        List<Candidates> candidates= candidateService.getCandidatesByElection(id);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/party/{id}")
    public  ResponseEntity<List<Candidates>> getByParty(@PathVariable Long id){
        List<Candidates> candidates = candidateService.getCandidatesByParty(id);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/search/{name}")
    public  ResponseEntity<List<Candidates>>getByNameOrPartialName(@PathVariable String name){
        List<Candidates> candidates = candidateService.searchCandidateByName(name);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CandidateResponse>getById(@PathVariable Long id  ){
        Candidates candidates = candidateService.getCandidateById(id);
        CandidateResponse response = new CandidateResponse();
        response.setName(candidates.getName());
        response.setCreatedAt(candidates.getCreatedAt());
        response.setUpdatedAt(candidates.getUpdatedAt());
        response.setId(candidates.getId());
        response.setApprovedBy(candidates.getApprovedBy());
        response.setStatus(candidates.getStatus());
        return  ResponseEntity.ok(response);

    }

}
