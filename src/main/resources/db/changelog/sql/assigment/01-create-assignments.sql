CREATE TABLE assignments
(
    id        SERIAL        PRIMARY KEY,
    course_id INT           NOT NULL,
    title     VARCHAR(255)  NOT NULL,
    type      VARCHAR(50),
    max_score INT DEFAULT 100,
    due_date  DATE
);
