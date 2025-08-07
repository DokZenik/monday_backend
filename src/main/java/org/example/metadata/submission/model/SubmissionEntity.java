package org.example.metadata.submission.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.exceptions.MondayException;
import org.example.metadata.grades.model.GradeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("submissions")
public class SubmissionEntity {

    @Id
    private Long id;

    @Column("student_id")
    private Long studentId;

    @Column("assignment_id")
    private Long assignmentId;

    @Column("text")
    private String text;

    @Column("files")
    private String attachedFiles;

    @Column("submitted_at")
    private LocalDateTime submittedAt;

    public SubmissionResponse toResponse(GradeEntity grade) {

        try {
            SubmissionResponse submissionResponse = SubmissionResponse.builder()
                    .id(id)
                    .studentId(studentId)
                    .assignmentId(assignmentId)
                    .text(text)
                    .attachedFiles(new ObjectMapper().readValue(attachedFiles, new TypeReference<>() { }))
                    .submittedAt(submittedAt)
                    .build();

            if (grade != null) {
                submissionResponse.setScore(grade.getScore());
                submissionResponse.setFeedback(grade.getFeedback());
                submissionResponse.setTimestamp(grade.getTimestamp());
                submissionResponse.setTeacherId(grade.getTeacherId());
            }
            return submissionResponse;
        } catch (JsonProcessingException e) {
            throw new MondayException("Can't deserialize attached files");
        }
    }

}
