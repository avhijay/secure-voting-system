package com.votingSystem.secureVote.service;

import com.votingSystem.secureVote.entity.Parties;

public interface PartiesService {

    Parties findByName(String name);
    Parties findById(Long id);
    Parties create(Parties parties);
}
