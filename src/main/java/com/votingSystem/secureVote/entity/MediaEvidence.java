package com.votingSystem.secureVote.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "media_evidence")
public class MediaEvidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "verification_id")
    private Verification verification;

    @Column(name = "media_type")
    private String MediaType;

    @Column(name = "storage_url")
    private String storageUrl;

    @Column(name = "metadata")
    private String metadata;


    public MediaEvidence(){}

    public MediaEvidence(Verification verification, String mediaType, String storageUrl, String metadata) {
        this.verification = verification;
        MediaType = mediaType;
        this.storageUrl = storageUrl;
        this.metadata = metadata;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getStorageUrl() {
        return storageUrl;
    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

    public String getMediaType() {
        return MediaType;
    }

    public void setMediaType(String mediaType) {
        MediaType = mediaType;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MediaEvidence{" +
                "id=" + id +
                ", verification=" + verification +
                ", MediaType='" + MediaType + '\'' +
                ", storageUrl='" + storageUrl + '\'' +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
