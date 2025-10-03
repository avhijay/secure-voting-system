package com.votingSystem.secureVote.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name ="votes")
public class Votes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "voter_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidates candidates;

    @Column(name = "cast_at")
    private Timestamp voteCastAt;

    public Votes(){}
    public Votes(Users users, Election election, Candidates candidates, Timestamp voteCastAt) {
        this.users = users;
        this.election = election;
        this.candidates = candidates;
        this.voteCastAt = voteCastAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Candidates getCandidates() {
        return candidates;
    }

    public void setCandidates(Candidates candidates) {
        this.candidates = candidates;
    }

    public Timestamp getVoteCastAt() {
        return voteCastAt;
    }

    public void setVoteCastAt(Timestamp voteCastAt) {
        this.voteCastAt = voteCastAt;
    }

    @Override
    public String toString() {
        return "Votes{" +
                "id=" + id +
                ", users=" + users +
                ", election=" + election +
                ", candidates=" + candidates +
                ", voteCastAt=" + voteCastAt +
                '}';
    }
}
