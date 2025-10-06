package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Verification;
import com.votingSystem.secureVote.service.VerificationService;

import java.util.List;

public class VerificationServiceImpl implements VerificationService {
    @Override
    public Verification createVerification(Verification verification) {
        return null;
    }

    @Override
    public List<Verification> getVerificationsByUser(Long userId) {
        return List.of();
    }

    @Override
    public List<Verification> getVerificationsByVote(Long voteId) {
        return List.of();
    }

    @Override
    public List<Verification> getVerificationByMethodAndStatus(String method, String status) {
        return List.of();
    }
}
