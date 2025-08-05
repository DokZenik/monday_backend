package org.example.metadata.grades.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GradeResponse {

    private Long id;

    private Long assignmentId;

    private Long studentId;

    private Integer score;

    private String feedback;

    private Long teacherId;

    private Long timestamp;
}
