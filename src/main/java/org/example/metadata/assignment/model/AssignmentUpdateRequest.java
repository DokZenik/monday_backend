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
public class AssignmentUpdateRequest {
    private String title;

    private AssignmentType type;

    private AssignmentStatus status;

    private String description;

    private Integer maxScore;

    private LocalDateTime dueDate;

    private List<AttachedFile> attachedFiles;

    public AssignmentEntity toEntity(AssignmentEntity oldEntity) {

        return AssignmentMapper.updateRequestToEntity(this, oldEntity);

    }
}
