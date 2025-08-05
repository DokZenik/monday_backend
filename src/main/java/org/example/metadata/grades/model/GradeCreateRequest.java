package org.example.metadata.grades.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeCreateRequest {
    private Long assignmentId;

    private Long studentId;

    private Integer score;

    private String feedback;

    private Long teacherId;

    private Long timestamp;

    public GradeEntity toEntity() {
        return new GradeEntity(null, assignmentId, studentId, score, feedback, teacherId, timestamp);
    }
}
