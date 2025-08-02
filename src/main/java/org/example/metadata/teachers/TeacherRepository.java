package org.example.metadata.teachers;

import org.example.metadata.teachers.model.TeacherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findById(Long id);
}
