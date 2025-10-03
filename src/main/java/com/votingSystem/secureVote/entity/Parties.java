package com.votingSystem.secureVote.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "parties")
public class Parties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "symbol", nullable = false, unique = true)
    private String symbol;

    @Column(name = "description")
    private String description;

    @Column(name = "founded_year")
    private Integer foundedYear;

    @Column(name = "created_at")
    private Timestamp createdAt;



    public Parties(){}

    public Parties(String name, String symbol, String description, Integer foundedYear, Timestamp createdAt) {
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.foundedYear = foundedYear;
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "Parties{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", description='" + description + '\'' +
                ", foundedYear=" + foundedYear +
                ", createdAt=" + createdAt +
                '}';
    }
}
