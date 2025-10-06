package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.Verification;

import java.util.List;

public interface VerificationService {

    Verification createVerification(Verification verification);

    List<Verification> getVerificationsByUser(Long userId);

    List<Verification> getVerificationsByVote(Long voteId);

    List<Verification> getVerificationByMethodAndStatus(String method , String status);




}
