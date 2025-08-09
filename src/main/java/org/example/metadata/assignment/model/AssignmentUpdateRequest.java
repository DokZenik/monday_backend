package org.example.metadata.assignment.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotBlank(message = "Title can't be null")
    private String title;

    @NotNull(message = "Assignment type can't be null")
    private AssignmentType type;

    @NotNull(message = "Assignment status can't be null")
    private AssignmentStatus status;

    @NotBlank(message = "Description can't be null")
    private String description;

    @NotNull(message = "Max score can't be null")
    @Positive(message = "Max score should be greater than 0")
    private Integer maxScore;

    @NotNull(message = "Due date can't be null")
    private LocalDateTime dueDate;

    private List<AttachedFile> attachedFiles;

    public AssignmentEntity toEntity(AssignmentEntity oldEntity) {

        return AssignmentMapper.updateRequestToEntity(this, oldEntity);

    }
}
