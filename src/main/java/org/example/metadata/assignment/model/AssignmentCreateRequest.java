package org.example.metadata.assignment.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.exceptions.MondayException;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentCreateRequest {
    private String title;

    private Long courseId;

    private Long teacherId;

    private String type;

    private String status;

    private String description;

    private Integer maxScore;

    private Integer grade;

    private LocalDateTime dueDate;

    private List<AttachedFile> attachedFiles;

    public AssignmentEntity toEntity() {

        try {
            String files = new ObjectMapper().writeValueAsString(attachedFiles);
            return new AssignmentEntity(null, title,courseId,teacherId,AssignmentType.fromString(type),AssignmentStatus.fromString(status),description,maxScore,grade,dueDate,files);
        } catch (JsonProcessingException e) {
            throw new MondayException("Files can't be saved");
        }
    }
}
