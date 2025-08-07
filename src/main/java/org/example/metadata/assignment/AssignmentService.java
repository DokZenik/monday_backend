package org.example.metadata.assignment;

import lombok.RequiredArgsConstructor;
import org.example.metadata.assignment.model.*;
import org.example.metadata.exceptions.MondayException;
import org.example.metadata.submission.SubmissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SubmissionService submissionService;

    public AssignmentResponse create(AssignmentCreateRequest request) {
        return assignmentRepository.save(request.toEntity()).toResponse(0);
    }

    public AssignmentResponse updateById(AssignmentUpdateRequest request, Long assignmentId) {

        AssignmentEntity assignmentEntity = assignmentRepository.findById(assignmentId).get();

        return assignmentRepository.save(request.toEntity(assignmentEntity))
                .toResponse(submissionService.getAssignmentSubmissionsCount(assignmentId));
    }

    public AssignmentsResponse getAll() {
        Iterator<AssignmentEntity> iterator = assignmentRepository.findAll().iterator();
        List<AssignmentResponse> coursesResponseList = new ArrayList<>();

        while (iterator.hasNext()) {
            AssignmentEntity assignmentEntity = iterator.next();
            coursesResponseList.add(assignmentEntity
                    .toResponse(submissionService.getAssignmentSubmissionsCount(assignmentEntity.getId())));
        }

        return new AssignmentsResponse(coursesResponseList);
    }

    public List<AssignmentResponse> getAllByCourseId(Long courseId) {
        Iterator<AssignmentEntity> iterator = assignmentRepository.findAllByCourseId(courseId).iterator();
        List<AssignmentResponse> coursesResponseList = new ArrayList<>();

        while (iterator.hasNext()) {
            AssignmentEntity assignmentEntity = iterator.next();
            coursesResponseList.add(assignmentEntity.toResponse(submissionService.
                    getAssignmentSubmissionsCount(assignmentEntity.getId())));
        }

        return coursesResponseList;
    }

    public AssignmentResponse getById(Long id) {
        return assignmentRepository.findById(id)
                .map(e -> e.toResponse(submissionService.getAssignmentSubmissionsCount(id)))
                .orElseThrow(() -> new MondayException(String.format("Assignment with id %d not found", id)));
    }

    public void deleteById(Long id) {
        try {
            assignmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new MondayException(String.format("Assignment with id %d not found", id));
        }
    }
}
