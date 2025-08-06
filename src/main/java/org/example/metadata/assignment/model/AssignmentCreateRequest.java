package org.example.metadata.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.assignment.AssignmentMapper;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentCreateRequest {
    private String title;

    private Long courseId;

    private Long teacherId;

    private AssignmentType type;

    private AssignmentStatus status;

    private String description;

    private Integer maxScore;

    private LocalDateTime dueDate;

    private List<AttachedFile> attachedFiles;

    public AssignmentEntity toEntity() {

        return AssignmentMapper.createRequestToEntity(this);
    }
}
