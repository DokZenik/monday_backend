CREATE TABLE grades
(
    id            SERIAL PRIMARY KEY,
    assignment_id INT NOT NULL REFERENCES assignments (id) ON DELETE CASCADE,
    student_id    INT NOT NULL REFERENCES students (id) ON DELETE CASCADE,
    score         INT,
    feedback      TEXT,
    graded_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_assignment_student UNIQUE (assignment_id, student_id)
);
