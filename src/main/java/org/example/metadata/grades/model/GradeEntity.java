package org.example.metadata.grades.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("grades")
public class GradeEntity {

    @Id
    private Long id;

    @Column("assignment_id")
    private Long assignmentId;

    @Column("student_id")
    private Long studentId;

    @Column("score")
    private Integer score;

    @Column("feedback")
    private String feedback;

    @Column("graded_at")
    private Long timestamp;

    public GradeResponse toResponse() {
        return new GradeResponse(id, assignmentId, studentId, score, feedback, timestamp);
    }
}
