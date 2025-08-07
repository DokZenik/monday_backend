package org.example.metadata.submission.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.assignment.model.AttachedFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionResponse {

    private Long id;

    private Long studentId;

    private Long assignmentId;

    private String text;

    private List<AttachedFile> attachedFiles;

    private LocalDateTime submittedAt;

    private Integer score;

    private String feedback;

    private Long timestamp;

    private Long teacherId;


}
