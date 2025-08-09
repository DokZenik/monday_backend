package org.example.metadata.grades.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeCreateRequest {

    @NotNull(message = "Assignment id can't be null")
    @PositiveOrZero(message = "Assignment id shouldn't be negative")
    private Long assignmentId;

    @NotNull(message = "Student id can't be null")
    @PositiveOrZero(message = "Student id shouldn't be negative")
    private Long studentId;

    @NotNull(message = "Score can't be null")
    @PositiveOrZero(message = "Score can't be negative")
    private Integer score;

    private String feedback;

    @NotNull(message = "Teacher id can't be null")
    @PositiveOrZero(message = "Teacher id shouldn't be negative")
    private Long teacherId;

    @NotNull(message = "Timestamp can't be null")
    @PositiveOrZero(message = "Timestamp should be greater than 0")
    private Long timestamp;

    public GradeEntity toEntity() {
        return new GradeEntity(null, assignmentId, studentId, score, feedback, teacherId, timestamp);
    }
}
