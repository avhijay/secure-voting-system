package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidates,Long> {

  List<Candidates> findByElectionId(Long electionId);

  List<Candidates> findByPartyId(Long partyId);

  List<Candidates> findByApprovedBy(Long userId);

  List<Candidates> findByNameContainingIgnoreCase(String partialName);


  @Query("SELECT cad FROM Candidates cad " +
          "WHERE cad.election.id = :electionId " +
          "AND (:status IS NULL OR cad.status = :status) " +
          "AND (:partyId IS NULL OR cad.party.id = :partyId) " +
          "ORDER BY cad.createdAt DESC")

  List<Candidates>getCandidatesByElectionAndStatusFilters(
          @Param("electionId") Long electionId,
          @Param("status") String status,
          @Param("partyId") Long partyId
  );






}
