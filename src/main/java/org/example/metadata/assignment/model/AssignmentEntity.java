package org.example.metadata.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.assignment.AssignmentMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

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

    @Column("teacher_id")
    private Long teacherId;

    @Column("type")
    private AssignmentType type;

    @Column("status")
    private AssignmentStatus status;

    @Column("description")
    private String description;

    @Column("max_score")
    private Integer maxScore;


    @Column("due_date")
    private LocalDateTime dueDate;

    @Column("attached_files")
    private String attachedFiles;

    public AssignmentResponse toResponse(Integer submissionCount) {

        return AssignmentMapper.entityToResponse(this, submissionCount);
    }
}
