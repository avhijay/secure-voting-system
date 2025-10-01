CREATE TABLE votes(
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 voter_id BIGINT  NOT NULL,
 election_id BIGINT   NOT NULL,
 candidate_id BIGINT  NOT NULL,
 cast_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT unique_vote UNIQUE (voter_id , election_id),
 CONSTRAINT fk_voter_id FOREIGN KEY(voter_id) REFERENCES users(id),
 CONSTRAINT fk_election_id FOREIGN KEY (election_id) REFERENCES elections(id),
 CONSTRAINT fK_candidate_id FOREIGN KEY (candidate_id) REFERENCES candidates(id)
);