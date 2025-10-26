package com.votingSystem.secureVote.repository;

import com.votingSystem.secureVote.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AuditRepository extends JpaRepository<Audit,Long> {

    Audit findTopByOrderByIdDesc();
     Optional<List<Audit>> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Audit>findByStatus(String status);

    @Query("Select aud FROM Audit aud " +
            "WHERE aud.user.id = :userId "+
            "AND (:action IS NULL OR  aud.action = :action) " +
            "And aud.createdAt BETWEEN :startDate AND :endDate " +
            "ORDER BY aud.createdAt DESC")

    List<Audit> findUsersAuditsWithinTimeRanges(
            @Param("userId") Long userId,
            @Param("action") String action,
            @Param("startDate")Timestamp startDate,
            @Param("endDate") Timestamp endDate);





    @Query("Select data FROM Audit data " +
            "WHERE (:action is NULL OR data.action = :action) " +
            "AND data.createdAt BETWEEN :startDate And :endDate " +
            "ORDER BY data.createdAt DESC")


    List<Audit>findAllAuditsWithinTimeRanges(
            @Param("action") String action,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate
    );
}


