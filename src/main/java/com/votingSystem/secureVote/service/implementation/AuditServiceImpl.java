package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Audit;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.repository.AuditRepository;
import com.votingSystem.secureVote.repository.UserRepository;
import com.votingSystem.secureVote.service.AuditService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.List;
@Service
public class AuditServiceImpl implements AuditService {

    private AuditRepository auditRepository;
    private UserRepository userRepository;

    public AuditServiceImpl(AuditRepository theauditRepository,UserRepository theUserRepository){
        this.auditRepository=theauditRepository;
        this.userRepository=theUserRepository;

    }



    


@Transactional
    @Override
    public Audit logAction(Long userId, String action, String status, String reason) {

Audit lastAudit= auditRepository.findTopByOrderByIdDesc();
String prevHash;
if(lastAudit==null){
    prevHash="GENESIS"; // if no previous hash isThere ( in short if it's the first audit log )
}else{
    prevHash=lastAudit.getEntryHash(); // for linking to previous hash
}

   ;



Audit newAudit = new Audit();

Users theUser = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Invalid Id "+userId));

newAudit.setUser(theUser);
newAudit.setAction(action);
newAudit.setStatus(status);
newAudit.setReason(reason);
Timestamp now = Timestamp.valueOf(LocalDateTime.now());
newAudit.setCreatedAt(now);
newAudit.setPrevHash(prevHash);
String data = prevHash +"|"+ userId +"|"+ action +"|"+ status +"|"+ reason +"|"+now;
newAudit.setEntryHash(computeHash(data));
return auditRepository.save(newAudit);

    }

     private  String computeHash(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            String hex = HexFormat.of().formatHex(encoded);
            return hex;
        }catch(NoSuchAlgorithmException e){
            throw  new RuntimeException("SHA-256 algorithm not found",e);

         }
     }

@Override
     public boolean verifyAuditChaining(){
        List<Audit> auditsList =auditRepository.findAll(Sort.by("id"));
        String lastHash="GENESIS";
        boolean chainValid= true;
        for(Audit curr:auditsList){
            String reHashed =computeHash(curr.getPrevHash() +"|"+ curr.getUser().getId() +"|"+curr.getAction() +"|"+ curr.getStatus() +"|"+ curr.getReason() +"|"+curr.getCreatedAt());
if(!reHashed.equals(curr.getEntryHash())){
    chainValid=false;
    break;
}
if(!curr.getPrevHash().equals(lastHash)){
    chainValid =false;
    break;
}
lastHash =curr.getEntryHash();


        }
        return chainValid;



     }



    @Override
    public List<Audit> getAllAuditLogs() {
        return auditRepository.findAll();
    }

    @Override
    public List<Audit> getAuditByUser(Long userId) {
        return auditRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public Audit getLatestAuditEntry() {
        return auditRepository.findTopByOrderByIdDesc();
    }
}
