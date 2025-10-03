package com.votingSystem.secureVote.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

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
    private String UserId;

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



public Users(){}

    public Users(String name, String password, Integer clearanceLevel, Timestamp createdAt, Timestamp updatedAt, String status, String role, String email, String userId) {
        this.name = name;
        this.password = password;
        this.clearanceLevel = clearanceLevel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.role = role;
        this.email = email;
        UserId = userId;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(Integer clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", UserId='" + UserId + '\'' +
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
