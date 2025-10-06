package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.MediaEvidence;

import java.util.List;

public interface MediaEvidenceService {

    MediaEvidence addMediaEvidence(MediaEvidence mediaEvidence);

    List<MediaEvidence> getMediaByVerification(Long verificationId);

    List<MediaEvidence> getMediaByType(String mediaType);
}
