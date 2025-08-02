CREATE TABLE assignments
(
    id        SERIAL        PRIMARY KEY,
    title     VARCHAR(255)  NOT NULL,
    course_id INT           NOT NULL,
    type      VARCHAR(50),
    max_score INT DEFAULT 100,
    due_date  DATE
);
