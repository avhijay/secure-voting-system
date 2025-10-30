package com.votingSystem.secureVote.rest;

import com.votingSystem.secureVote.dto.VerificationResponse;
import com.votingSystem.secureVote.entity.Verification;
import com.votingSystem.secureVote.service.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/verifications")
public class VerificationController {


    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService){
        this.verificationService=verificationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Verification>>getById(@PathVariable Long id){

      List<  Verification >verification = verificationService.getVerificationsByUser(id);
      return ResponseEntity.ok(verification);

    }

    @GetMapping("/{voteId}")
    public ResponseEntity<List<Verification>>getVerificationByVoteId(@PathVariable Long id){
        List<Verification> verifications = verificationService.getVerificationsByVote(id);
        return ResponseEntity.ok(verifications);
    }

    @GetMapping("/{method}/{status}")

    public  ResponseEntity<List<Verification>> getByMethodAndStatus(@PathVariable String method , @PathVariable String status){
        List<Verification> verifications = verificationService.getVerificationByMethodAndStatus(method,status);
        return ResponseEntity.ok(verifications);
    }




}
