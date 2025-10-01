CREATE TABLE media_evidence(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
verification_id BIGINT NOT NULL,
media_type VARCHAR(50) NOT NULL,
storage_url VARCHAR(255) NOT NULL,
metadata TEXT,
CONSTRAINT fk_verification_id FOREIGN KEY (verification_id) REFERENCES verification(id)
);