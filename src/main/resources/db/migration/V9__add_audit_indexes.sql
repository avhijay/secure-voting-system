CREATE INDEX  idx_audit_user_created_at ON audit(user_id, created_at);

CREATE INDEX idx_audit_created_at ON audit(created_at);

CREATE INDEX  idx_audit_status ON audit(status);