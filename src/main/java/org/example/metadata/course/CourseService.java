package org.example.metadata.course;

import lombok.RequiredArgsConstructor;
import org.example.metadata.course.model.CourseCreateRequest;
import org.example.metadata.course.model.CourseEntity;
import org.example.metadata.course.model.CourseResponse;
import org.example.metadata.course.model.CoursesResponse;
import org.example.metadata.exceptions.MondayException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseResponse create(CourseCreateRequest request) {
        return courseRepository.save(request.toEntity()).toResponse();
    }

    public CoursesResponse getAll() {
        Iterator<CourseEntity> iterator = courseRepository.findAll().iterator();
        List<CourseResponse> coursesResponseList = new ArrayList<>();

        while (iterator.hasNext()) {
            coursesResponseList.add(iterator.next().toResponse());
        }

        return new CoursesResponse(coursesResponseList);
    }

    public CourseResponse getById(Long id) {
        return courseRepository.findById(id)
                .map(CourseEntity::toResponse)
                .orElseThrow(() -> new MondayException(String.format("Course with id %d not found", id)));
    }

    public void deleteById(Long id) {
        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            throw new MondayException(String.format("Course with id %d not found", id));
        }
    }
}
