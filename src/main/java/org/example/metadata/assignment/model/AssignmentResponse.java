package org.example.metadata.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignmentResponse {

    private Long id;

    private String title;

    private Long courseId;

    private Long teacherId;

    private AssignmentType type;

    private AssignmentStatus status;

    private String description;

    private Integer maxScore;

    private LocalDateTime dueDate;

    private List<AttachedFile> attachedFiles;

    private String timeRemaining;

    private Integer submissions;
}
