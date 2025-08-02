CREATE TABLE courses
(
    id          SERIAL      PRIMARY KEY,
    title       TEXT        NOT NULL,
    teacher_id  INT         NOT NULL,
    group_name  VARCHAR(50) NOT NULL
);
