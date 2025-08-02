package org.example.metadata.course;

import org.example.metadata.course.model.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
    Optional<CourseEntity> findById(Long id);
}
