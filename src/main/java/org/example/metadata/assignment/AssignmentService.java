package org.example.metadata.assignment;

import lombok.RequiredArgsConstructor;
import org.example.metadata.assignment.model.*;
import org.example.metadata.exceptions.MondayException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentResponse create(AssignmentCreateRequest request) {
        return assignmentRepository.save(request.toEntity()).toResponse();
    }

    public AssignmentResponse updateById(AssignmentUpdateRequest request, Long assignmentId) {

        AssignmentEntity assignmentEntity = assignmentRepository.findById(assignmentId).get();

        return assignmentRepository.save(request.toEntity(assignmentEntity)).toResponse();
    }

    public AssignmentsResponse getAll() {
        Iterator<AssignmentEntity> iterator = assignmentRepository.findAll().iterator();
        List<AssignmentResponse> coursesResponseList = new ArrayList<>();

        while (iterator.hasNext()) {
            coursesResponseList.add(iterator.next().toResponse());
        }

        return new AssignmentsResponse(coursesResponseList);
    }

    public List<AssignmentResponse> getAllByCourseId(Long courseId) {
        Iterator<AssignmentEntity> iterator = assignmentRepository.findAllByCourseId(courseId).iterator();
        List<AssignmentResponse> coursesResponseList = new ArrayList<>();

        while (iterator.hasNext()) {
            coursesResponseList.add(iterator.next().toResponse());
        }

        return coursesResponseList;
    }

    public AssignmentResponse getById(Long id) {
        return assignmentRepository.findById(id)
                .map(AssignmentEntity::toResponse)
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
