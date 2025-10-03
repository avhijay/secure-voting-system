package com.votingSystem.secureVote.entity;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.sql.Timestamp;

@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;   // FK → users.id

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "prev_hash")
    private String prevHash;

    @Column(name = "entry_hash")
    private String entryHash;



public Audit(){}

    public Audit(Users user, String action, String status, String reason, Timestamp createdAt, String prevHash, String entryHash) {
        this.user = user;
        this.action = action;
        this.status = status;
        this.reason = reason;
        this.createdAt = createdAt;
        this.prevHash = prevHash;
        this.entryHash = entryHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public String getEntryHash() {
        return entryHash;
    }

    public void setEntryHash(String entryHash) {
        this.entryHash = entryHash;
    }


    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", user=" + user +
                ", action='" + action + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", createdAt=" + createdAt +
                ", prevHash='" + prevHash + '\'' +
                ", entryHash='" + entryHash + '\'' +
                '}';
    }
}
