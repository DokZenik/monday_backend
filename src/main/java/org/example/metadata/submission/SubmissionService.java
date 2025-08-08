package org.example.metadata.submission;

import lombok.RequiredArgsConstructor;
import org.example.metadata.exceptions.MondayException;
import org.example.metadata.grades.GradeService;
import org.example.metadata.grades.model.GradeEntity;
import org.example.metadata.submission.model.SubmissionCreateRequest;
import org.example.metadata.submission.model.SubmissionResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final GradeService gradeService;

    public SubmissionResponse create(SubmissionCreateRequest request, Long assigmentId) {
        return submissionRepository.save(request.toEntity(assigmentId)).toResponse(null);
    }

    public SubmissionResponse findByAssignmentIdAndStudentId(Long assignmentId, Long studentId) {
        GradeEntity grade = gradeService.getByAssignmentIdAndStudentId(assignmentId, studentId);
        return submissionRepository.findFirst(assignmentId, studentId).orElseThrow(
                        () -> new MondayException(String.format
                                ("Submission with assignment id %d and student id %d wasn't found",
                                        assignmentId,
                                        studentId))).toResponse(grade);
    }

    public void deleteByAssignmentIdAndStudentId(Long assignmentId, Long studentId) {
        submissionRepository.deleteByAssignmentIdAndStudentId(assignmentId, studentId);
    }
    public Integer getAssignmentSubmissionsCount(Long assignmentId) {
        return submissionRepository.countByAssignmentId(assignmentId);
    }


}
