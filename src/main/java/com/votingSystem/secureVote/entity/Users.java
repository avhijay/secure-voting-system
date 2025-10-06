package com.votingSystem.secureVote.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id")
 private Long id;

@Column(name="name")
    private String name;

@Column(name="user_id")
    private String userId;

@Column(name = "email")
    private String email;

@Column(name="password")
    private String password;

@Column(name = "role")
    private  String role;

@Column(name="status")
    private String status;

@Column(name="clearance_level")
    private Integer clearanceLevel;

@Column(name="created_at")
    private Timestamp createdAt;

@Column(name = "updated_at")
    private Timestamp updatedAt;
/*
@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
private List<Verification> verifications;

 */



public Users(){}

    public Users(String name, String userId, String email, String password, String role, String status, Integer clearanceLevel, Timestamp createdAt, Timestamp updatedAt, List<Verification> verifications) {
        this.name = name;
        userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.clearanceLevel = clearanceLevel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
       // this.verifications = verifications;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(Integer clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
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

   /*public List<Verification> getVerifications() {
        return verifications;
    }

   public void setVerifications(List<Verification> verifications) {
        this.verifications = verifications;
    }
    */


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", UserId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", clearanceLevel=" + clearanceLevel +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
