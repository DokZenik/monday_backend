ALTER TABLE courses
    RENAME COLUMN teacher_id TO teacher_ids;

ALTER TABLE courses
    ALTER COLUMN teacher_ids TYPE TEXT USING teacher_ids::TEXT;

ALTER TABLE courses
    DROP COLUMN group_name;

ALTER TABLE courses
    ADD COLUMN description TEXT,
    ADD COLUMN category TEXT NOT NULL,
    ADD COLUMN student_ids TEXT,
    ADD COLUMN creator_id INT,
    ADD COLUMN start_date DATE NOT NULL,
    ADD COLUMN end_date DATE NOT NULL,
    ADD COLUMN rating DOUBLE PRECISION,
    ADD COLUMN level TEXT NOT NULL,
    ADD COLUMN thumbnail TEXT,
    ADD COLUMN color TEXT NOT NULL,
    ADD COLUMN skills TEXT NOT NULL,
    ADD COLUMN price DOUBLE PRECISION NOT NULL,
    ADD COLUMN published BOOLEAN NOT NULL;
