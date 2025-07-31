package org.example.metadata.student;

import lombok.RequiredArgsConstructor;
import org.example.metadata.exceptions.MondayException;
import org.example.metadata.student.model.StudentCreateRequest;
import org.example.metadata.student.model.StudentEntity;
import org.example.metadata.student.model.StudentResponse;
import org.example.metadata.student.model.StudentsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponse create(StudentCreateRequest request) {
        return studentRepository.save(request.toEntity()).toResponse();
    }

    public StudentsResponse getAll() {
        Iterator<StudentEntity> iterator = studentRepository.findAll().iterator();
        List<StudentResponse> studentResponseList = new ArrayList<>();

        while (iterator.hasNext()) {
            studentResponseList.add(iterator.next().toResponse());
        }

        return new StudentsResponse(studentResponseList);
    }

    public StudentResponse getById(Long id) {
        return studentRepository.findById(id)
                .map(StudentEntity::toResponse)
                .orElseThrow(() -> new MondayException(String.format("Student with id %d not found", id)));
    }

}
