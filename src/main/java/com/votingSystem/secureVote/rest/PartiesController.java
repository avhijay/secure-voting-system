package com.votingSystem.secureVote.rest;

import com.votingSystem.secureVote.dto.PartiesRequest;
import com.votingSystem.secureVote.dto.PartiesResponse;
import com.votingSystem.secureVote.entity.Candidates;
import com.votingSystem.secureVote.entity.Parties;
import com.votingSystem.secureVote.service.CandidateService;
import com.votingSystem.secureVote.service.PartiesService;
import jakarta.validation.Valid;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/parties")
public class PartiesController {

    private  final PartiesService partiesService;
    private final CandidateService candidateService;

    public PartiesController(PartiesService partiesService , CandidateService candidateService) {
        this.partiesService = partiesService;
        this.candidateService = candidateService;
    }

        @PostMapping
                public  ResponseEntity<PartiesResponse> createParty(@Valid @RequestBody PartiesRequest partiesRequest ){
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());                    ;
            Parties newParty = new Parties();

            newParty.setCreatedAt(now);
            newParty.setDescription(partiesRequest.getDescription());
            newParty.setFoundedYear(partiesRequest.getFoundedYear());
            newParty.setName(partiesRequest.getName());
            newParty.setSymbol(partiesRequest.getSymbol());

            partiesService.create(newParty);

            PartiesResponse response = new PartiesResponse();
            response.setDescription(newParty.getDescription());
            response.setFoundedYear(newParty.getFoundedYear());
            response.setSymbol(newParty.getSymbol());
           response.setCreatedAt(newParty.getCreatedAt());
           response.setName(newParty.getName());


            URI location = URI.create("/api/parties"+newParty.getId());

            return  ResponseEntity.created(location).body(response);
            }







            @GetMapping("/{id}")
    public  ResponseEntity<PartiesResponse>getById(@PathVariable Long id){

        Parties newParty = partiesService.findById(id);
                PartiesResponse response = new PartiesResponse();
                response.setDescription(newParty.getDescription());
                response.setFoundedYear(newParty.getFoundedYear());
                response.setSymbol(newParty.getSymbol());
                response.setCreatedAt(newParty.getCreatedAt());
                response.setName(newParty.getName());

                return  ResponseEntity.ok(response);

            }

    @GetMapping("/{name}")
    public  ResponseEntity<PartiesResponse>getByName(@PathVariable String name){

        Parties newParty = partiesService.findByName(name);
        PartiesResponse response = new PartiesResponse();
        response.setDescription(newParty.getDescription());
        response.setFoundedYear(newParty.getFoundedYear());
        response.setSymbol(newParty.getSymbol());
        response.setCreatedAt(newParty.getCreatedAt());
        response.setName(newParty.getName());

        return  ResponseEntity.ok(response);

    }



        }





