CREATE TABLE courses
(
    id         SERIAL       PRIMARY KEY,
    subject_id INT          NOT NULL,
    teacher_id INT          NOT NULL,
    group_name VARCHAR(50)  NOT NULL
);
