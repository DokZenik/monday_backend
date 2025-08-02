CREATE TABLE grades
(
    id            SERIAL PRIMARY KEY,
    assignment_id INT NOT NULL,
    student_id    INT NOT NULL,
    score         INT,
    feedback      TEXT,
    graded_at     BIGINT,
    CONSTRAINT uq_assignment_student UNIQUE (assignment_id, student_id)
);
