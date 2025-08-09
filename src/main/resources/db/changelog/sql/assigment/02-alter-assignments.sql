ALTER TABLE assignments
    ADD COLUMN teacher_id BIGINT NOT NULL,
    ADD COLUMN status VARCHAR(50) NOT NULL,
    ADD COLUMN description TEXT NOT NULL,
    ADD COLUMN attached_files TEXT;

ALTER TABLE assignments
    ALTER COLUMN due_date TYPE TIMESTAMP
    USING due_date::timestamp;
