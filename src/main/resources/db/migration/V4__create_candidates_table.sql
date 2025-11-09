CREATE TABLE candidates (

id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,

party_id BIGINT NOT NULL,
bio TEXT,
status VARCHAR (50) NOT NULL,

election_id BIGINT,
approved_by BIGINT,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP  NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
CONSTRAINT fk_candidate_party FOREIGN KEY (party_id) REFERENCES parties(id),
CONSTRAINT chk_candidate_name CHECK (name LIKE '% %'),
CONSTRAINT chk_candidate_status CHECK (UPPER(status)IN ('PENDING','APPROVED','REJECTED','BANNED')),
CONSTRAINT  fk_candidate_election FOREIGN KEY(election_id) REFERENCES elections(id),
 CONSTRAINT fk_candidate_approver FOREIGN KEY(approved_by) REFERENCES users(id)


);
