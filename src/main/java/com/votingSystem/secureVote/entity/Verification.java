package com.votingSystem.secureVote.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "verification")
public class Verification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Votes vote;   // FK → votes.id

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;   // FK → users.id

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "captured_at")
    private Timestamp capturedAt;

    @Column(name = "file_location")
    private String fileLocation;

    /* @OneToMany(mappedBy = "verification",fetch = FetchType.LAZY)
    private List<MediaEvidence> mediaEvidenceList;

     */


    public  Verification(){}


    public Verification(Votes vote, Users user, String method, String status, Timestamp capturedAt, String fileLocation, List<MediaEvidence> mediaEvidenceList) {
        this.vote = vote;
        this.user = user;
        this.method = method;
        this.status = status;
        this.capturedAt = capturedAt;
        this.fileLocation = fileLocation;
       // this.mediaEvidenceList = mediaEvidenceList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Votes getVote() {
        return vote;
    }

    public void setVote(Votes vote) {
        this.vote = vote;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCapturedAt() {
        return capturedAt;
    }

    public void setCapturedAt(Timestamp capturedAt) {
        this.capturedAt = capturedAt;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

  /*  public List<MediaEvidence> getMediaEvidenceList() {
        return mediaEvidenceList;
    }

    public void setMediaEvidenceList(List<MediaEvidence> mediaEvidenceList) {
        this.mediaEvidenceList = mediaEvidenceList;
    }

   */

    @Override
    public String toString() {
        return "Verification{" +
                "id=" + id +
                ", vote=" + vote +
                ", user=" + user +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", capturedAt=" + capturedAt +
                ", fileLocation='" + fileLocation + '\'' +
                '}';
    }
}
