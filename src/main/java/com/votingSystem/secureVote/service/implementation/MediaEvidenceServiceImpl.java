package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.MediaEvidence;
import com.votingSystem.secureVote.repository.MediaEvidenceRepository;
import com.votingSystem.secureVote.service.MediaEvidenceService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MediaEvidenceServiceImpl implements MediaEvidenceService {

    private MediaEvidenceRepository mediaEvidenceRepository;

    public MediaEvidenceServiceImpl (MediaEvidenceRepository mediaEvidenceRepository1){
        this.mediaEvidenceRepository=mediaEvidenceRepository1;
    }


    @Override
    public MediaEvidence addMediaEvidence(MediaEvidence mediaEvidence) {
        return mediaEvidenceRepository.save(mediaEvidence);
    }

    @Override
    public List<MediaEvidence> getMediaByVerification(Long verificationId) {
        return mediaEvidenceRepository.findByVerificationId(verificationId);
    }

    @Override
    public List<MediaEvidence> getMediaByType(String mediaType) {
        return null;
    }
}
