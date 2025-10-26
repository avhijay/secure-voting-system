package com.votingSystem.secureVote.rest;


import com.votingSystem.secureVote.dto.AuditResponse;
import com.votingSystem.secureVote.entity.Audit;
import com.votingSystem.secureVote.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/audits")
public class AuditController {
    private AuditService auditService;
    public AuditController(AuditService auditService1){
        this.auditService=auditService1;

    }
    @GetMapping
    public ResponseEntity <List<Audit>> getAllAudits(){
      List<Audit> audits = auditService.getAllAuditLogs();
      return ResponseEntity.ok(audits);
    }

    @GetMapping("/audit/{id}")
    public ResponseEntity<List<Audit>>getById(@PathVariable Long id){
        List<Audit> audits = auditService.getAuditByUser(id);
        return ResponseEntity.ok(audits);
    }

    @GetMapping("/latest")
    public ResponseEntity<Audit>getLatest(){
        Audit audit = auditService.getLatestAuditEntry();
        return  ResponseEntity.ok(audit);
    }

    @GetMapping("/verify")
    public ResponseEntity<AuditResponse>verifyAudits(){
        Optional<Long>brokenAt = auditService.verifyAuditChaining();

        AuditResponse response = new AuditResponse();
        if (brokenAt.isPresent()){
            response.setBrokenAtId(brokenAt.get());
            response.setMessage("Audit hashing  chain broken at "+brokenAt.get());
            response.setStatus("Broken");
        }else{

            response.setMessage("Audit hashing is not broken ");
            response.setStatus("Valid");

        }
        return  ResponseEntity.ok(response);




    }


}
