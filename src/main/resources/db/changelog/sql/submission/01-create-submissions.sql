CREATE TABLE submissions
(
    id SERIAL PRIMARY KEY,
    student_id INT NOT NULL,
    assignment_id INT NOT NULL,
    text TEXT,
    files TEXT,
    submitted_at TIMESTAMP
);
