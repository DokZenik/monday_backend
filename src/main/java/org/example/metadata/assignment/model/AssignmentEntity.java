package org.example.metadata.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("assignments")
public class AssignmentEntity {

    @Id
    private Long id;

    @Column("title")
    private String title;

    @Column("course_id")
    private Long courseId;

    @Column("type")
    private AssignmentType type;

    @Column("max_score")
    private Integer maxScore;

    @Column("due_date")
    private Date dueDate;

    public AssignmentResponse toResponse() {
        return new AssignmentResponse(id, title, courseId, type, maxScore, dueDate);
    }
}
