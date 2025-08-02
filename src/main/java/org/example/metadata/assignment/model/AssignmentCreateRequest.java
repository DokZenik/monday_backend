package org.example.metadata.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentCreateRequest {
    private String title;

    private Long courseId;

    private AssignmentType type;

    private Integer maxScore;

    private Date dueDate;

    public AssignmentEntity toEntity() {
        return new AssignmentEntity(null, title, courseId, type, maxScore, dueDate);
    }
}
