package org.example.metadata.grades;

import lombok.RequiredArgsConstructor;
import org.example.metadata.exceptions.MondayException;
import org.example.metadata.grades.model.GradeCreateRequest;
import org.example.metadata.grades.model.GradeEntity;
import org.example.metadata.grades.model.GradeResponse;
import org.example.metadata.grades.model.GradesResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeResponse create(GradeCreateRequest request) {
        return gradeRepository.save(request.toEntity()).toResponse();
    }

    public GradesResponse getAll() {
        Iterator<GradeEntity> iterator = gradeRepository.findAll().iterator();
        List<GradeResponse> gradesResponseList = new ArrayList<>();

        while (iterator.hasNext()) {
            gradesResponseList.add(iterator.next().toResponse());
        }

        return new GradesResponse(gradesResponseList);
    }

    public GradeEntity getByAssignmentIdAndStudentId(Long assignmentId, Long studentId) {
        return gradeRepository.findByAssignmentIdAndStudentId(assignmentId, studentId).orElse(null);
    }

    public void deleteById(Long id) {
        try {
            gradeRepository.deleteById(id);
        } catch (Exception e) {
            throw new MondayException(String.format("Assignment with id %d not found", id));
        }
    }
}
