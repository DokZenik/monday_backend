package org.example.metadata.student;

import lombok.RequiredArgsConstructor;
import org.example.metadata.student.model.StudentEntity;
import org.example.metadata.student.model.StudentResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponse create(StudentEntity student) {
        return studentRepository.save(student).toResponse();
    }
}
