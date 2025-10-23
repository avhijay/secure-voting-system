package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.entity.Parties;
import com.votingSystem.secureVote.exception.ResourceNotFoundException;
import com.votingSystem.secureVote.repository.PartyRepository;
import com.votingSystem.secureVote.service.PartiesService;
import org.springframework.stereotype.Service;

@Service
public class PartiesServiceImpl implements PartiesService {
    private PartyRepository partyRepository;
    public PartiesServiceImpl(PartyRepository partyRepository1){
        this.partyRepository=partyRepository1;
    }


    @Override
    public Parties findByName(String name) {
        return partyRepository.findByName(name) ;
    }

    @Override
    public Parties findById(Long id) {
        return partyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No party Exist by Id :"+id));
    }
}
