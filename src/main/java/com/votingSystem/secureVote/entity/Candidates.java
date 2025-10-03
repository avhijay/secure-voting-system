package com.votingSystem.secureVote.entity;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="candidates")
public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn (name = "party_id")
    private Parties party;

    @Column(name = "bio")
    private String bio;


    @Column(name = "status")
    private String status;

@ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;



    @Column(name = "approved_by")
    private long approvedBy;


    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;


    public Candidates(){}

    public Candidates(String name, Parties party, String bio, String status, Election election, long approvedBy, Timestamp createdAt, Timestamp updatedAt) {
        this.name = name;
        this.party = party;
        this.bio = bio;
        this.status = status;
        this.election = election;
        this.approvedBy = approvedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parties getParty() {
        return party;
    }

    public void setParty(Parties party) {
        this.party = party;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Candidates{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", party=" + party +
                ", bio='" + bio + '\'' +
                ", status='" + status + '\'' +
                ", election=" + election +
                ", approvedBy=" + approvedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
