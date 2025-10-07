package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Verification;
import com.votingSystem.secureVote.repository.VerificationRepository;
import com.votingSystem.secureVote.service.VerificationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VerificationServiceImpl implements VerificationService {

    private VerificationRepository verificationRepository;

    public VerificationServiceImpl(VerificationRepository verificationRepository1){
        this.verificationRepository=verificationRepository1;
    }



    @Override
    public Verification createVerification(Verification verification) {
        return verificationRepository.save(verification);
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
}
