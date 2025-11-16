package com.votingSystem.secureVote.service.implementation;

import com.votingSystem.secureVote.Enums.ElectionStatus;
import com.votingSystem.secureVote.dsa.VoteTracker;
import com.votingSystem.secureVote.dto.vote.GetByElectionIdRequest;
import com.votingSystem.secureVote.dto.vote.GetByUserIdRequest;
import com.votingSystem.secureVote.dto.vote.GetByVoteIdRequest;
import com.votingSystem.secureVote.dto.vote.VoteResponse;
import com.votingSystem.secureVote.entity.Candidates;
import com.votingSystem.secureVote.entity.Election;
import com.votingSystem.secureVote.entity.Users;
import com.votingSystem.secureVote.entity.Votes;
import com.votingSystem.secureVote.exception.*;
import com.votingSystem.secureVote.repository.CandidateRepository;
import com.votingSystem.secureVote.repository.ElectionRepository;
import com.votingSystem.secureVote.repository.UserRepository;
import com.votingSystem.secureVote.repository.VoteRepository;
import com.votingSystem.secureVote.security.AuthContext;
import com.votingSystem.secureVote.service.AuditService;
import com.votingSystem.secureVote.service.VoteService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private static final Logger log = LoggerFactory.getLogger(VoteServiceImpl.class);
    private VoteRepository voteRepository;
    private CandidateRepository candidateRepository;
    private ElectionRepository electionRepository;
    private UserRepository userRepository;
    private AuditService auditService;
    private  final VoteTracker voteTracker;

    @Enumerated(EnumType.STRING)
    private ElectionStatus electionStatus;

    public VoteServiceImpl(VoteRepository voteRepository1, CandidateRepository candidates1, ElectionRepository electionRepository1, UserRepository userRepository1, AuditService auditService1  , VoteTracker voteTracker) {
        this.voteRepository = voteRepository1;
        candidateRepository = candidates1;
        this.electionRepository = electionRepository1;
        this.userRepository = userRepository1;
        this.auditService = auditService1;
        this.voteTracker = voteTracker;
    }

    @Transactional  // to do = METHOD TO BIG , MAKE 2 SEPARATE METHODS
    @Override
    public Votes castVote(Long voterId, Long electionId, Long candidateId) {
        Long sessionId = AuthContext.userId();
        log.info("ACTION= CAST_VOTE | BY USER_ID={}  |IN ELECTION ={} | STATUS = START ",voterId,electionId);

        Votes newVote = new Votes();

        Candidates newCandidate;

        log.debug("Validating Candidate {} for election {}",candidateId,electionId);
        newCandidate = candidateRepository.findById(candidateId).orElseThrow(() ->  new CandidateNotFound("Candidate id not found " + candidateId));


        if (!newCandidate.getElection().getId().equals(electionId)) {
            auditService.logAction(voterId, "Casting vote", "Failure", "wrong candidate selection (Mismatch with election id ) ");
            log.warn("Candidate {} mismatch with election {}",candidateId,electionId);
            throw new CandidateNotFound ("Candidate not available for the current election: " + candidateId + "in election: " + electionId);
        }



        if (!voteTracker.hasVoted(electionId,voterId)) {
            newVote.setCandidates(newCandidate);
log.debug("Validating election {}",electionId);
            Election newElection = electionRepository.findById(electionId).orElseThrow(() -> new ElectionNotFound("Election not found" + electionId));


            //Check if election is onGoing
            if (newElection.getStatus() == ElectionStatus.UpComing) {
                log.error(Exception.class.getName());
                auditService.logAction(voterId, "Casting vote", "Failure", "Election not active :" + electionId);
                throw new ElectionNotActiveException ("Election is currently not active :" + electionId);
            }

            newVote.setElection(newElection);
            Users newUser = userRepository.findById(voterId).orElseThrow(() -> new UserNotFound("No voter found :" + voterId));
            newVote.setUsers(newUser);
            newVote.setVoteCastAt(Timestamp.valueOf(LocalDateTime.now()));

            //if user has voted
        } else {
            log.warn("User {} already voted in election {}",voterId,electionId);
            auditService.logAction(voterId, "Casting vote ", "Failure", "");
            throw new DuplicateVoteException("User has already Voted");
        }

        // after all checks
        log.debug("Saving vote in election {}",electionId);
        Votes saved = voteRepository.save(newVote);
        voteTracker.casting(electionId,voterId);
        auditService.logAction(voterId, "Casting vote", "Success", "Voting process by the user ");
        log.info("ACTION= CAST_VOTE | BY USER_ID={}  |IN ELECTION ={} | STATUS = SUCCESS ",voterId,electionId);
        log.debug("All validations passed for User {} to vote in election {} for Candidate {}",voterId,electionId,candidateId);
        return saved;
    }









    @Override
    public boolean hasUserVoted(Long voterId, Long electionId) {
        Long sessionId = AuthContext.userId();

        log.info("ACTION= CHECK_VOTE_STATUS | BY USER_ID={}  |IN ELECTION ={} | FOR USER={}| STATUS = START ",sessionId,electionId,voterId);
        log.debug("Check for User{} voting Status in election{}",voterId,electionId);
        return voteRepository.findByUsersIdAndElectionId(voterId, electionId) != null;

    }

    @Override //TO DO -> CHECK LOGIC IF RETURNED ARE EVERY ELECTION OR SINGLE ELECTION
    public Long countVotesForCandidate(Long candidateId) {
        Long sessionId = AuthContext.userId();
        log.info("ACTION= COUNT_VOTES_FOR_CANDIDATE | BY USER_ID={}| FOR CANDIDATE_ID = {}",sessionId,candidateId);

        return voteRepository.countByCandidatesId(candidateId);

    }

    @Override
    public Long countVotesForElection(Long electionId) {
        Long sessionId = AuthContext.userId();
        log.info("ACTION= COUNT_VOTES_FOR_ELECTION | BY USER_ID={}| FOR ELECTION_ID = {}",sessionId,electionId);
        return voteRepository.countByElectionId(electionId);
    }

    @Override
    public List<VoteResponse> returnAllVotesByElectionId(Long electionId) {
        Long sessionId = AuthContext.userId();

        log.info("ACTION=GET_ALL_VOTES_FOR_ELECTION | BY USER_ID={}| FOR ELECTION_ID = {}",sessionId,electionId);

        if (voteRepository.findByElectionId(electionId) == null) {
            throw new ResourceNotFoundException("No votes found for the particular election id : " + electionId);
        }
        return voteRepository.findByElectionId(electionId)
                .stream()
                .map(VoteResponse::new)
                .toList();
    }

    @Override
    public Votes getVoteByVoteId(Long voteId) {
        Long sessionId = AuthContext.userId();
        log.info("ACTION=GET_VOTE_BY_VOTE_ID | BY USER_ID={}",sessionId);

        return voteRepository.findById(voteId).orElseThrow(() -> new ResourceNotFoundException("No votes available by the id :" + voteId));
    }

    @Override
    public Votes getElectionVotesByVoterId(Long electionId, Long voterId) {
        Long sessionId = AuthContext.userId();
        log.info("ACTION=GET_VOTE_BY_VOTER_ID |IN ELECTION_ID={}| FOR VOTER_ID={}| BY USER_ID={}",electionId,voterId,sessionId);
        return voteRepository.findByUsersIdAndElectionId(voterId,electionId).orElseThrow(()->new ResourceNotFoundException("No votes available by the voterId :" + voterId));
    }

    @Override
    public Votes getByUserId(Long userId) {
        Long sessionId = AuthContext.userId();
        log.info("ACTION= GET_VOTES_BY_USER_ID ={}|BY USER_ID={}",userId,sessionId);


        return voteRepository.findByUsersId(userId).orElseThrow(()->new UserNotFound("No votes found by id : "+userId ));
    }

    @Override
    public Boolean hasTheUserVoted(Long electionId, Long userId) {
        return null;
    }


    @Override
    public Long countForCandidateForElection(Long candidateId, Long electionId) {
        Long sessionId = AuthContext.userId();
        log.info("ACTION=COUNT_FOR_CANDIDATE ={}|IN_ELECTION={} | BY_USER={} ",candidateId,electionId,sessionId);
        if(voteRepository.countByCandidatesIdAndElectionId(candidateId,electionId)==null){
            throw  new ResourceNotFoundException("No count found for  candidateId :"+candidateId+"election :"+electionId);
        }
        return  voteRepository.countByCandidatesIdAndElectionId (candidateId,electionId);

    }


    @PostConstruct
    public void inbuiltVoteMemory(){


        List<Votes>findAll = voteRepository.findAll();

        for (Votes vote : findAll) {
            voteTracker.casting(vote.getElection().getId(), vote.getUsers().getId());
        }
        System.out.println("VoteInBuiltMemory Initialized");

    }




}







