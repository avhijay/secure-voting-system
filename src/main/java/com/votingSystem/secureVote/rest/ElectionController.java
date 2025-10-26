package com.votingSystem.secureVote.rest;

import com.votingSystem.secureVote.dto.ElectionRequest;
import com.votingSystem.secureVote.dto.ElectionResponse;
import com.votingSystem.secureVote.entity.Election;
import com.votingSystem.secureVote.service.ElectionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/elections")
public class ElectionController {

    private final ElectionService electionService;
    public ElectionController(ElectionService electionService1){
        this.electionService=electionService1;
    }



    @PostMapping
    public ResponseEntity<ElectionResponse>createUser( @Valid @RequestBody ElectionRequest request){

        Election election = new Election();
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        election.setStatus(request.getStatus());
        election.setCreatedAt(now);
        election.setCreatedBy(null);
        election.setName(request.getName());
        election.setDescription(request.getDescription());
        election.setEndDate(request.getEndDate());
        election.setStartDate(now);
        election.setUpdatedAt(now);
        electionService.createElection(election);

        ElectionResponse response =new ElectionResponse();
        response.setDescription(election.getDescription());
        response.setCreatedBy(election.getCreatedBy());
        response.setEndDate(election.getEndDate());
        response.setStartDate(election.getStartDate());
        response.setCreatedAt(election.getCreatedAt());
       response.setStatus(election.getStatus());
       response.setName(election.getName());





        URI location = URI.create("/api/elections"+election.getId());
       return ResponseEntity.created(location).body(response);

    }

    @GetMapping("/{id}")
public ResponseEntity<ElectionResponse>getElectionById(@PathVariable Long electionId){
     Election election=   electionService.getElectionById(electionId);

        ElectionResponse response =  new ElectionResponse();

        response.setDescription(election.getDescription());
        response.setCreatedBy(election.getCreatedBy());
        response.setEndDate(election.getEndDate());
        response.setStartDate(election.getStartDate());
        response.setCreatedAt(election.getCreatedAt());
        response.setStatus(election.getStatus());
        response.setName(election.getName());

        return  ResponseEntity.ok(response);

    }

    @GetMapping("/{name}")
    public ResponseEntity<ElectionResponse>getElectionByName(@PathVariable String electionName){


        Election election=   electionService.getElectionByName(electionName);

        ElectionResponse response =  new ElectionResponse();

        response.setDescription(election.getDescription());
        response.setCreatedBy(election.getCreatedBy());
        response.setEndDate(election.getEndDate());
        response.setStartDate(election.getStartDate());
        response.setCreatedAt(election.getCreatedAt());
        response.setStatus(election.getStatus());
        response.setName(election.getName());

        return  ResponseEntity.ok(response);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Election>> getAllElection(){
        List<Election> elections = electionService.getAllElection();
        return  ResponseEntity.ok(elections);
    }



    @GetMapping("/ongoing")
    public ResponseEntity<List<Election>> getOngoingElections(){
        List<Election> elections = electionService.getOnGoingElection();
        return  ResponseEntity.ok(elections);
    }


    @GetMapping("/ongoing")
    public ResponseEntity<List<Election>> getUpComingElections(){
        List<Election> elections = electionService.getUpcomingElection();
        return  ResponseEntity.ok(elections);
    }

    @PatchMapping("/{id}/{status}")

    public  ResponseEntity<ElectionResponse>updateElectionStatus(@PathVariable Long id , @PathVariable String status){

        Election election = electionService.updateElectionStatus(id,status);

        ElectionResponse response = new ElectionResponse();

        response.setDescription(election.getDescription());
        response.setCreatedBy(election.getCreatedBy());
        response.setEndDate(election.getEndDate());
        response.setStartDate(election.getStartDate());
        response.setCreatedAt(election.getCreatedAt());
        response.setStatus(election.getStatus());
        response.setName(election.getName());

        return ResponseEntity.ok(response);

    }


}
