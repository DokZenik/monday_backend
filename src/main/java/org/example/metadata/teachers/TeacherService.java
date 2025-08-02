package org.example.metadata.teachers;

import lombok.RequiredArgsConstructor;
import org.example.metadata.exceptions.MondayException;
import org.example.metadata.teachers.model.TeacherCreateRequest;
import org.example.metadata.teachers.model.TeacherEntity;
import org.example.metadata.teachers.model.TeachersResponse;
import org.example.metadata.teachers.model.TeacherResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherResponse create(TeacherCreateRequest request) {
        return teacherRepository.save(request.toEntity()).toResponse();
    }

    public TeachersResponse getAll() {
        Iterator<TeacherEntity> iterator = teacherRepository.findAll().iterator();
        List<TeacherResponse> teachersResponseList = new ArrayList<>();

        while (iterator.hasNext()) {
            teachersResponseList.add(iterator.next().toResponse());
        }

        return new TeachersResponse(teachersResponseList);
    }

    public TeacherResponse getById(Long id) {
        return teacherRepository.findById(id)
                .map(TeacherEntity::toResponse)
                .orElseThrow(() -> new MondayException(String.format("Teacher with id %d not found", id)));
    }

    public void deleteById(Long id) {
        try {
            teacherRepository.deleteById(id);
        } catch (Exception e) {
            throw new MondayException(String.format("Teacher with id %d not found", id));
        }
    }
}
