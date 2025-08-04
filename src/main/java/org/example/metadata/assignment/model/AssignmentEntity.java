package org.example.metadata.assignment.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.exceptions.MondayException;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column("grade")
    private Integer grade;

    @Column("due_date")
    private LocalDateTime dueDate;

    @Column("attached_files")
    private String attachedFiles;

    public AssignmentResponse toResponse() {


        LocalDateTime now = LocalDateTime.now();

        Duration between = Duration.between(now, dueDate);

        String timeRemaining = String.format("%d days, %d hours", between.toHours() / 24, Math.abs(between.toHours() % 24));

        try {
            List<AttachedFile> attachedFiles = new ObjectMapper().readValue(getAttachedFiles(), new TypeReference<>() {
            });

            return new AssignmentResponse(id, title, courseId, teacherId, type.getType(), status.getStatus(), description, maxScore, grade, dueDate, attachedFiles, timeRemaining, null);

        } catch (JsonProcessingException e) {
            throw new MondayException("Can't deserialize attached files");
        }


    }
}
