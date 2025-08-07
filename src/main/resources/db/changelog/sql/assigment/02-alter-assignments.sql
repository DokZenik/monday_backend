ALTER TABLE assignments
    ADD COLUMN teacher_id BIGINT,
    ADD COLUMN status VARCHAR(50),
    ADD COLUMN description TEXT,
    ADD COLUMN attached_files TEXT;

ALTER TABLE assignments
    ALTER COLUMN due_date TYPE TIMESTAMP
    USING due_date::timestamp;
