package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.dsa.VerificationQueue;

import com.votingSystem.secureVote.entity.Verification;
import com.votingSystem.secureVote.repository.VerificationRepository;
import com.votingSystem.secureVote.service.VerificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VerificationServiceImpl implements VerificationService {

    private VerificationRepository verificationRepository;
    private VerificationQueue verificationQueue;

    public VerificationServiceImpl(VerificationRepository verificationRepository1  , VerificationQueue verificationQueueConfig){
        this.verificationRepository=verificationRepository1;
        this.verificationQueue=verificationQueueConfig;
    }



    @Override
    public Verification createVerification(Verification verification) {
        verification.setStatus("Pending");
        Verification saved = verificationRepository.save(verification);
        verificationQueue.enqueueVerification(saved.getId());
        return saved;
    }

    @Override
    public List<Verification> getVerificationsByUser(Long userId) {
        return verificationRepository.findByUserId(userId);
    }

    @Override
    public List<Verification> getVerificationsByVote(Long voteId) {
        return verificationRepository.findByVoteId(voteId);
    }

    @Override
    public List<Verification> getVerificationByMethodAndStatus(String method, String status) {
        return verificationRepository.findByMethodAndStatus(method,status);
    }

    @Scheduled(fixedRate = 5000)
    public void processVerifications(){

        Long nextUserId = verificationQueue.pollNextUser();
        if(nextUserId!=null){
            List<Verification> pending = verificationRepository.findByUserId(nextUserId);


            for(Verification entries : pending){
                if("PENDING".equalsIgnoreCase(entries.getStatus())){
                    entries.setStatus("SUCCESS ");
                    // real verification logic to be implemented
verificationRepository.save(entries);
                    System.out.println("User verified :"+nextUserId);
                }
if ("REJECTED".equalsIgnoreCase(entries.getStatus())){
    System.out.println("Reject :"+entries.getId());
}
            }
        }else{
            System.out.println("No verifications pending ");
        }

    }
}
