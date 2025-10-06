package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.MediaEvidence;
import com.votingSystem.secureVote.service.MediaEvidenceService;

import java.util.List;

public class MediaEvidenceServiceImpl implements MediaEvidenceService {
    @Override
    public MediaEvidence addMediaEvidence(MediaEvidence mediaEvidence) {
        return null;
    }

    @Override
    public List<MediaEvidence> getMediaByVerification(Long verificationId) {
        return List.of();
    }

    @Override
    public List<MediaEvidence> getMediaByType(String mediaType) {
        return List.of();
    }
}
