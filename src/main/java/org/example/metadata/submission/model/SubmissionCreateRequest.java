package org.example.metadata.submission.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.assignment.model.AttachedFile;
import org.example.metadata.exceptions.MondayException;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionCreateRequest {

    @NotNull(message = "Student id shouldn't be null")
    @PositiveOrZero(message = "Student id shouldn't be negative")
    private Long studentId;

    private String text;

    private List<AttachedFile> files;

    public SubmissionEntity toEntity(Long assignmentId) {

        try {
            String files = new ObjectMapper().writeValueAsString(getFiles());

            return new SubmissionEntity(null, studentId, assignmentId, text, files, LocalDateTime.now());
        } catch (JsonProcessingException e) {
            throw new MondayException("Files can't be saved");
        }
    }
}
