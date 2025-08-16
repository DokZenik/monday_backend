package org.example.metadata.grades.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeCreateRequest {

    @NotNull(message = "Assignment id can't be null")
    private Long assignmentId;

    @NotNull(message = "Student id can't be null")
    private Long studentId;

    @NotNull(message = "Score can't be null")
    private Integer score;

    private String feedback;

    @NotNull(message = "Teacher id can't be null")
    private Long teacherId;

    @NotNull(message = "Timestamp can't be null")
    private Long timestamp;

    public GradeEntity toEntity() {
        return new GradeEntity(null, assignmentId, studentId, score, feedback, teacherId, timestamp);
    }
}
