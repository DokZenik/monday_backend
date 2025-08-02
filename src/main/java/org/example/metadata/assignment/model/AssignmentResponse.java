package org.example.metadata.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignmentResponse {

    private Long id;

    private String title;

    private Long courseId;

    private AssignmentType type;

    private Integer maxScore;

    private Date dueDate;
}
